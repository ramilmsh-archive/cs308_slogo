package interpreter.core.elements;

import interpreter.core.Expression;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;

/**
 * Implements List object
 *
 * @author ramilmsh
 */
public class List extends Value<ArrayList<Element>> {

    public List(ArrayList<Element> value) {
        super(value);
    }

    @Override
    public Value resolve(Scope scope) {
        ArrayList<Value> list = new ArrayList<>();
        for (Element element : value)
            list.add((element.resolve(scope)));

        if (list.size() == 0)
            return new Value<>(0.);

        // While I would very much like to perform operations on lists, there simply are none in SLogo :.(
        return new Value<>(list);
    }

    /**
     * Get a List from input
     *
     * @param context: context
     * @return a List
     */
    public static List process(Context context) {
        ArrayList<Element> list = new ArrayList<>();
        String token = context.tokenizer.next();
        if (!token.equals("[")) {
            context.console.error("[ expected " + token + " found");
            return null;
        }

        while (context.tokenizer.hasNext() && !context.tokenizer.peek().equals("]"))
            try {
                list.add(Expression.parse(context));
            } catch (InvalidSyntaxError e) {
                return null;
            }

        // TODO try throwing exception and terminating the whole thing
        if (!context.tokenizer.peek().equals("]")) {
            context.console.error("] expected, " + context.tokenizer.peek() + "found");
            return null;
        }
        context.tokenizer.next();
        return new List(list);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
