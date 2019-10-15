package foundationOop.week2;

import org.junit.Test;
import sheffield.EasyReader;

import java.io.*;

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
            fileWriter.write("Transaction" + "\tValue" + "\tBalance\n");

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
                fileWriter.write(transactionType + "\t" + realAmount + "\t" + balance + "\n");
            }

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

    private static Writer createWriter() {
        String fileName = "output.txt";
//        File file = new File(fileName);
//        if (!file.exists()){
//            file.createNewFile();
//        }
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


    @Test
    public void test01(){
        System.out.println(String.format("name is  %32.9s", "12313"));
        System.out.println(String.format("name is  %f",   32.0));
        System.out.println(String.format("name is  %8f",   32.123));
        System.out.println(String.format("name is  %8s",   32.123));
        System.out.println(String.format("name is  %32.7g",   32.12312217));
        System.out.println(String.format("name is  %32.9f",   32.123122112362));

    }

    @Test
    public void test02(){
        System.out.println(String.format("name is 23232 %-32s", "12313"));
        System.out.println(String.format("name is 22 %-32f",   32.0));
        System.out.println(String.format("name is 5435345345 %-32f",   32.123));
        System.out.println(String.format("name is 43443 %-32s",   32.123));
        System.out.println(String.format("name is  345345 %-32.7g",   32.12312217));
        System.out.println(String.format("name is  4535345 %-32.9f",   32.123122112362));

    }
}
