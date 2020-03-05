package assignment03;

/**
 * USER: ZQJ
 * DATE: 3/4/2020
 * TIME: 8:12 PM
 */
public class ExceptionUtil {
    /**
     * print out exception prompt and exit system
     *
     * @param e      exception
     * @param prompt prompt
     */
    public static void exceptionExit(Exception e, String prompt) {
        System.err.println(prompt);
        System.err.println(e.getMessage());
        System.exit(0);
    }
}
