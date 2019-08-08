package interpreter.util;
import controller.channel.PubSub;

/**
 * Class that combines all essential objects, needed to interpret code
 *
 * @author ramilmsh
 */
public class Context {
    public Scope scope;
    public Console console;
    public Translator translator;
    public Tokenizer tokenizer;
    public PubSub pubsub;

    /**
     * Create context
     *
     * @param pubsub: communication link
     * @param translator: translator
     */
    public Context(PubSub pubsub, Translator translator) {
        this.pubsub = pubsub;
        this.translator = translator;
    }

    public Context(PubSub pubsub, Translator translator, Scope scope) {
        this(pubsub, translator);
        update(scope);
    }

    /**
     * Change scope in the context
     *
     * @param scope: scope
     */
    public void update(Scope scope) {
        this.scope = scope;
    }

    /**
     * Update tokenizer
     *
     * @param tokenizer: tokenizer
     */
    public void update(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }
}
