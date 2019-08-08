package interpreter.core.include;

import controller.channel.PubSub;
import controller.channel.messages.Query;
import interpreter.core.elements.Function;
import interpreter.core.elements.Value;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements Turtle queries
 *
 * @author ramilmsh, Natalie
 */
public class TurtleQuery extends Function {

    /**
     * Creates a new instance of a Turtle query
     *
     * @param name: query name
     * @param context: context
     */
    public TurtleQuery(String name, Context context) {
        super(name, context);
    }

    @Override
    public Value resolve(Scope scope) {
        Value ret = context.pubsub.publishSync(PubSub.Channel.QUERY, new Query(getName()));
        double[] v = (double[]) ret.getValue();
        ArrayList<Value> list = new ArrayList<>();
        for (double el : v)
            list.add(new Value<>(el));
    	return new Value<>(list);
    }

    @Override
    public String toString() {
        return getName();
    }
}
