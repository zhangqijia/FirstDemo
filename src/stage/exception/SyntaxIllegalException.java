package stage.exception;

/**
 * @author ZQJ
 * @date 3/26/2020
 */
public class SyntaxIllegalException extends Exception {

    public SyntaxIllegalException(String message) {
        super("[SyntaxIllegalException]: \"" + message + "\" is incorrect!!!");
    }
}
