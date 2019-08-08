package interpreter.exceptions;

/**
 * Invalid syntax error
 *
 * @author ramilmsh
 */
public class InvalidSyntaxError extends Exception {
    public InvalidSyntaxError(String msg) {
        super(msg);
    }
}
