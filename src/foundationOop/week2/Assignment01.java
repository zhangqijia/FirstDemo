package foundationOop.week2;

import foundationOop.assignment03.sheffield.EasyReader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Qijia Zhang
 * @since 15/10/2019
 */
public class Assignment01 {

    private static final String SALARY = "Salary";
    private static final String GIFT = "Gift";

    public static void main(String[] args) {
        //init balance
        double balance = 0;

        //Create an input stream and output steam
        EasyReader reader = new EasyReader();
        Writer fileWriter = null;
        try {
            fileWriter = createWriter();

            //write the first line
            fileWriter.write(String.format("%-25s %10s %10s %n", "Transaction", "Value", "Balance"));

            //Read filename
            String fileName = reader.readString("please type in full file path and name: ");

            //Create file reader
            EasyReader fileReader = new EasyReader(fileName);

            //variables receive the input value
            String transactionType = null;
            String amount = null;

            //Read until the last pair of the file
            while (!(transactionType = fileReader.readString()).equals("") && !(amount = fileReader.readString()).equals("")) {
                //convert the type of amount and distinguish between positive and negative number
                double realAmount;
                if (transactionType.equals(SALARY) || transactionType.equals(GIFT)) {
                    realAmount = Double.valueOf(amount);
                } else {
                    realAmount = 0 - Double.valueOf(amount);
                }

                //calculate balance and write one line
                balance += realAmount;

                //for String type add whitespace to keep lines tidy, for Double type reserve two decimal fractions and ensure the length
                String formatted = String.format("%-25s %10.2f %10.2f %n", transactionType, realAmount, balance);
                fileWriter.write(formatted);
            }
            System.out.println("create output file successfully");
        } catch (IOException e) {
            System.err.println("create output file failed");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * create File writer stream
     *
     * @return
     */
    private static Writer createWriter() {
        String fileName = "output.txt";
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("create file failed");
            System.exit(0);
        }
        return bufferedWriter;
    }
}
