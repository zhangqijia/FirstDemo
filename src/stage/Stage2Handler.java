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
        int lineNum = 0;
        int successNum = 0;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            String line;
            // read every line of query sentences
            while ((line = bufferedReader.readLine()) != null) {
                lineNum++;
                System.out.println("Query " + lineNum + ": " + line);
                try {
                    // execute this query sentence and return corresponding result
                    results = QueryRunner.run(line);
                    // print query result
                    System.out.println(results);
                    successNum++;
                } catch (InvocationTargetException | IntrospectionException |
                        IllegalAccessException | SyntaxIllegalException | NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            // print query execution statistics
            System.out.println("Query finished, total executed query: " + lineNum + ", successfully execute: " + successNum);
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
