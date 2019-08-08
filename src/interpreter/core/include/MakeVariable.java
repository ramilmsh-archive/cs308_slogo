package interpreter.core.include;

import interpreter.core.elements.Element;
import interpreter.core.elements.Function;
import interpreter.core.elements.Value;
import interpreter.core.elements.Variable;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.util.Context;
import interpreter.util.Scope;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Implements MakeVariable function
 */
public class MakeVariable extends Function {
    private ArrayList<Element> arguments;

    /**
     * Creates a new instance of MakeVariable funciton
     *
     * @param name: function name
     * @param context: context
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public MakeVariable(String name, Context context) throws InvalidSyntaxError {
        super(name, context);
        arguments = Function.readArguments(2, context);
    }

    @Override
    public Value resolve(Scope scope) {
        Iterator<Element> iterator = arguments.iterator();
        ArrayList<Value> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Element _variable = iterator.next();
            if (!(_variable instanceof Variable))
                return null;
            Variable variable = (Variable) _variable;
            Element value = iterator.next();
            list.add(value.resolve(scope));
            scope.setValue(variable, list.get(list.size() - 1));
        }

        if (list.size() == 1)
            return list.get(0);
        return new Value<ArrayList>(list);
    }

    @Override
    public String toString() {
        return getName() + " " + arguments.get(0) + " " + arguments.get(1);
    }
}
