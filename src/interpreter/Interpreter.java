package interpreter;

import controller.channel.PubSub;
import controller.channel.messages.Interpret;
import controller.channel.messages.Message;
import interpreter.core.Expression;
import interpreter.core.elements.Element;
import interpreter.exceptions.InvalidSyntaxError;
import interpreter.exceptions.ValueException;
import interpreter.util.*;

import java.util.ArrayList;

/**
 * Interprets the code and maintains the environment
 *
 * @author ramilmsh
 */
public class Interpreter {

    private Context context;

    /**
     * Intializes the interpreter
     *
     * @param pubsub: communication link with the rest of the project
     */
    public Interpreter(PubSub pubsub) {
        Translator translator;
        try {
            translator = new Translator(pubsub);
        } catch (ValueException e) {
            // console.error("English is not defined, exiting");
            return;
        }

        context = new Context(pubsub, translator);
        context.update(new Scope(pubsub));

        pubsub.subscribe(PubSub.Channel.LANGUAGE_CHANGE, translator::listenChangeLanguage);
        pubsub.subscribe(PubSub.Channel.INTERPRET, this::process);
        pubsub.subscribe(PubSub.Channel.VARIABLE_LOAD, context.scope::load);
    }

    /**
     * Processes a String of input
     * Can be a single valid command, line of code or an entire file
     *
     * @param msg: input
     */
    private void process(Message msg) {
        Interpret message = (Interpret) msg;
        context.update(new Tokenizer(message.code));
        while (context.tokenizer.hasNext())
            try {
                Element expression = Expression.parse(context);
                System.out.println("exp: " + expression);
                if (expression != null)
                    System.out.println("out: " + expression.resolve(context.scope));
            } catch (InvalidSyntaxError | NullPointerException e) {
                System.err.println(e.toString());
                e.printStackTrace();
                break;
            }
    }

    /**
     * Resets the environment
     */
    private void reset() {
        context.update(new Scope(context.pubsub));
    }
}
