package interpreter.util;

import controller.channel.PubSub;
import controller.channel.messages.FunctionUpdate;
import controller.channel.messages.Message;
import controller.channel.messages.VariableLoad;
import controller.channel.messages.VariableUpdate;
import interpreter.core.elements.Function;
import interpreter.core.elements.List;
import interpreter.core.elements.Value;
import interpreter.core.elements.Variable;
import interpreter.core.include.CustomFunction;
import interpreter.core.include.CustomFunctionDefinition;
import interpreter.core.include.TurtleCommand;
import interpreter.core.include.TurtleQuery;
import interpreter.exceptions.InvalidSyntaxError;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Class implements functionality to create and maintain
 * variables and user-defined functions
 *
 * @author ramilmsh
 */
public class Scope {

    public enum Type {
        VARIABLE, FUNCTION, COMMAND, QUERY
    }

    /**
     * List of built-in functions
     */
    public enum Functions {
        Sum(interpreter.core.include.Sum.class),
        Difference(interpreter.core.include.Difference.class),
        ArcTangent(interpreter.core.include.ArcTangent.class),
        Cosine(interpreter.core.include.Cosine.class),
        Minus(interpreter.core.include.Minus.class),
        NaturalLog(interpreter.core.include.NaturalLog.class),
        Pi(interpreter.core.include.Pi.class),
        Power(interpreter.core.include.Power.class),
        Product(interpreter.core.include.Product.class),
        Quotient(interpreter.core.include.Quotient.class),
        Random(interpreter.core.include.Random.class),
        Remainder(interpreter.core.include.Remainder.class),
        Sine(interpreter.core.include.Sine.class),
        Tangent(interpreter.core.include.Tangent.class),

        MakeVariable(interpreter.core.include.MakeVariable.class),
        Define(interpreter.core.include.Define.class),
        Repeat(interpreter.core.include.Repeat.class),
        DoTimes(interpreter.core.include.DoTimes.class),
        For(interpreter.core.include.For.class),
        If(interpreter.core.include.If.class),
        IfElse(interpreter.core.include.IfElse.class),
        MakeUserInstruction(interpreter.core.include.MakeUserInstruction.class),

        And(interpreter.core.include.And.class),
        Or(interpreter.core.include.Or.class),
        Not(interpreter.core.include.Not.class),
        LessThan(interpreter.core.include.LessThan.class),
        GreaterThan(interpreter.core.include.GreaterThan.class),
        Equal(interpreter.core.include.Equal.class),
        NotEqual(interpreter.core.include.NotEqual.class);

        private Class<? extends Function> clazz;

        Functions(Class<? extends Function> clazz) {
            this.clazz = clazz;
        }
    }

    /**
     * List of all turtle commands
     */
    private enum Commands {
        Forward(1),
        Backward(1),
        Left(1),
        Right(1),
        SetHeading(1),
        SetTowards(2),
        SetPosition(2),
        PenDown(0),
        PenUp(0),
        ShowTurtle(0),
        HideTurtle(0),
        Home(0),
        ClearScreen(0),
        ID(0),
        Turtles(0),
        SetBackground(1),
        SetPenColor(1),
        SetPenSize(1),
        SetShape(1),
        SetPallete(4);

        public int numArgs;

        Commands(int numArgs) {
            this.numArgs = numArgs;
        }
    }

    /**
     * List of turtle queries
     */
    private enum Queries {
        XCoordinate,
        YCoordinate,
        Heading,
        PenDown,
        Showing,
        PenColor,
        TurtleShape
    }

    private static final HashSet<String> queries = getKeys(Queries.class);
    private static final HashSet<String> commands = getKeys(Commands.class);
    private static final HashSet<String> builtIn = getKeys(Functions.class);

    private Scope parent;
    private String name;
    private HashMap<String, Scope> children;
    private HashMap<String, Value> variables;
    private HashMap<String, CustomFunctionDefinition> functions;

    private PubSub pubsub;

    /**
     * Create an empty scope
     */
    public Scope(PubSub pubsub) {
        this.pubsub = pubsub;
        name = "root";

        children = new HashMap<>();
        variables = new HashMap<>();
        functions = new HashMap<>();
    }

    /**
     * Create an empty scope as a child of another scope
     *
     * @param name:   scope name
     * @param parent: parent scope
     */
    public Scope(PubSub pubsub, String name, Scope parent) {
        this(pubsub);
        this.parent = parent;
        parent.children.put(name, this);
        this.name = name;
    }

    /**
     * Get value of a variable
     *
     * @param variable: variable
     * @return value of variable in the scope, or any of the parent scopes
     */
    public Value getValue(Variable variable) {
        Scope cur = this;

        while (cur != null) {
            if (cur.variables.containsKey(variable.getName()))
                return cur.variables.get(variable.getName());
            else
                cur = cur.parent;
        }

        return null;
    }

    /**
     * Record value of a variable
     *
     * @param variable: variable
     * @param value:    value to be recorded
     */
    public void setValue(Variable variable, Value value) {
        variables.put(variable.getName(), value);
        pubsub.publish(PubSub.Channel.VARIABLE_UPDATE, new VariableUpdate(variable.getName(), value.toString()));
    }

    /**
     * Get definition of a user-defined function
     *
     * @param name: function name
     * @return definition
     */
    public CustomFunctionDefinition getDefinition(String name) {
        return functions.get(name);
    }

    /**
     * Create function from name
     *
     * @param name:    function name
     * @param context: context
     * @return new function object
     * @throws InvalidSyntaxError: if there has been a syntax error
     */
    public Function getFunction(String name, Context context) throws InvalidSyntaxError {
        if (builtIn.contains(name))
            try {
                return Functions.valueOf(name).clazz.getConstructor(String.class, Context.class).newInstance(name, context);
            } catch (InstantiationException | IllegalAccessException |
                    NoSuchMethodException | InvocationTargetException e) {
                System.err.println("Failed to initialize " + name);
                System.err.println(e.toString());
            }

        if (commands.contains(name))
            return new TurtleCommand(name, context, Commands.valueOf(name).numArgs);

        if (queries.contains(name))
            return new TurtleQuery(name, context);

        if (functions.containsKey(name))
            return new CustomFunction(functions.get(name));

        return null;
    }

    /**
     * Define a function
     *
     * @param function: function definition
     */
    public void setFunction(CustomFunctionDefinition function) {
        functions.put(function.name, function);
        pubsub.publish(PubSub.Channel.FUNCTION_UPDATE, new FunctionUpdate(function.name,
                function.code == null ? "" : function.code.toString()));
    }

    /**
     * Implement defined method
     *
     * @param name: function name
     * @param code: code of the function
     */
    public void implementFunction(String name, List code) {
        functions.get(name).code = code;
    }

    public void load(Message msg) {
        VariableLoad message = (VariableLoad) msg;
        Context context = new Context(pubsub, null, this);

        for (Map.Entry<String, String> entry : message.variables.entrySet()) {
            context.update(new Tokenizer(entry.getValue()));
            // TODO publish Invalid syntax error?
            setValue(Variable.process(context), Value.process(context));
        }

        for (Map.Entry<String, String> entry : message.variables.entrySet())
            try {
                context.update(new Tokenizer(entry.getValue()));
                setFunction(new CustomFunctionDefinition(entry.getKey(), context,
                        CustomFunctionDefinition.process(context), List.process(context)));
            } catch (InvalidSyntaxError e) {
                // publish error
            }
    }

    /**
     * Get a list of the keys of an enum
     *
     * @param enumeration: enum
     * @return new HashSet of keys
     */
    private static HashSet<String> getKeys(Class<? extends Enum> enumeration) {
        HashSet<String> list = new HashSet<>();
        for (Enum el : enumeration.getEnumConstants())
            list.add(el.name());
        return list;
    }
}
