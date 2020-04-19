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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZQJ
 * @date 4/13/2020
 */
public class Stage2Handler implements Handler {

    static ExecutorService threadExecutor = new ThreadPoolExecutor(2, 4, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(4));

    public static LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>();
    public static AtomicInteger totalNum = new AtomicInteger();
    public static AtomicInteger successNum = new AtomicInteger();
    public static List<QueryResults> arrayList = new ArrayList<>();

    @Override
    public void execute(String[] args) {
        //verify the number of files, the query.txt should be the last file in this array
        if (args.length > 3) {
            // create QueryRunner tasks
            for (int i = 0; i < 2; i++) {
                threadExecutor.execute(() -> {
                    while (true) {
                        try {
                            synchronized (QueryRunner.class) {
                                String queryStr = blockingQueue.take();
                                int num = totalNum.incrementAndGet();
                                System.out.println(num + " :" + queryStr);
                                QueryResults results = QueryRunner.run(queryStr);
                                System.out.println(results);
                            }
                        } catch (InterruptedException | InvocationTargetException | IntrospectionException | IllegalAccessException | SyntaxIllegalException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                });
            }
            this.readQueryFile(args[3]);
        }
        threadExecutor.shutdown();
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
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
            String line;
            // read every line of query sentences
            while ((line = bufferedReader.readLine()) != null) {
                lineNum++;
                try {
                    // execute this query sentence and return corresponding result
                    blockingQueue.add(line);
                    // print query result
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            // print query execution statistics
            System.out.println("Query finished, total executed query: " + lineNum);
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
