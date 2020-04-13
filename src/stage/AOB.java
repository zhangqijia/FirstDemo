package stage;

/**
 * Reading file and store data.
 *
 * @author ZQJ
 */
public class AOB {

    /**
     * Read starts, messier, planet and print statistics info
     *
     * @param args file name list
     */
    public static void main(String[] args) {
        // create different handlers to cope with different questions
        // execute stage1's task and print out answers
        Handler stage1Handler = new Stage1Handler();
        stage1Handler.execute(args);
        // execute stage2's task
        Handler stage2Handler = new Stage2Handler();
        stage2Handler.execute(args);
    }
}