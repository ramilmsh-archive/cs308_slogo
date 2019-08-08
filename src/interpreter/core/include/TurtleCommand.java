package interpreter.core.include;

import command.CommandUnit;
import controller.channel.PubSub;
import controller.channel.messages.Execute;
import interpreter.core.Expression;
import interpreter.core.elements.Element;
import interpreter.core.elements.Function;
import interpreter.core.elements.List;
import interpreter.core.elements.Value;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements Turtle commands
 *
 * @author ramilmsh
 */
public class TurtleCommand extends Function {
    private int numArgs;
    private ArrayList<Element> args;

    /**
     * Creates a new instance of a Turtle command
     *
     * @param name: command name
     * @param context: context
     * @param numArgs: number of arguments the command accepts
     */
    public TurtleCommand(String name, Context context, int numArgs) {
        super(name, context);
        this.numArgs = numArgs;
        args = new ArrayList<>();
        if (numArgs > 0)
            try {
                for (int i = 0; i < numArgs; ++i)
                    args.add(Expression.parse(context));
            } catch (InvalidSyntaxError e) {
            	System.out.println("!");
                context.console.error(e.toString());
            }
    }

    @Override
    public Value resolve(Scope scope) {
    	Double[] d = new Double[numArgs];
    	for (int i = 0; i < args.size(); i++){ 		
    		Object o = args.get(i).resolve(scope).getValue();
    		if (o instanceof ArrayList){
    			ArrayList a = (ArrayList) o;
    			Value v = (Value) a.get(0);
    			d[i] = (Double) v.getValue();
    		}
    		else
    			d[i] = (Double) args.get(i).resolve(scope).getValue();
    	}
    	Value ret = context.pubsub.publishSync(PubSub.Channel.EXECUTE, new Execute(new CommandUnit(getName(), d)));
    	double[] v = (double[]) ret.getValue();
    	ArrayList<Value> list = new ArrayList<>();
    	for (double el : v)
    	    list.add(new Value<>(el));
    	return new Value<>(list);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("( ");
        sb.append(getName()).append(" ");
        if (args != null)
        	for (Element element : args)
        		sb.append(element).append(" ");
        sb.subSequence(0, sb.length() - 2);
        sb.append(")");
        return sb.toString();
    }
}
