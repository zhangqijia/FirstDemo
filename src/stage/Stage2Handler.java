package stage;

import stage.exception.SyntaxIllegalException;
import stage.query.QueryResults;
import stage.query.QueryRunner;

import java.beans.IntrospectionException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

/**
 * @author ZQJ
 * @date 4/13/2020
 */
public class Stage2Handler implements Handler {

    @Override
    public void execute(String[] args) {
        //verify the number of files, the query.txt should be the last file in this array
        if (args.length > 3) {
            this.readQueryFile(args[3]);
        }
    }

    /**
     * execute query file
     *
     * @param filepath query file path
     */
    private void readQueryFile(String filepath) {
        BufferedReader bufferedReader = null;
        // declare QueryResults variable
        QueryResults results = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            String line = null;
            // read every line of query sentences
            while ((line = bufferedReader.readLine()) != null) {
                try {
                    // execute this query sentence and return corresponding result
                    results = QueryRunner.run(line);
                    // print query result
                    System.out.println(results);
                } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
                    System.out.println(e.getMessage() + " " + line);
                } catch (SyntaxIllegalException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "fail to read query file");
        } finally {
            try {
                // close the Reader stream
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
