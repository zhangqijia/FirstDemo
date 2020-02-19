package week1;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class IOTest {

    public static void main(String[] args) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("RandomNum.txt"));
            Random random = new Random();
            int i1;
            for (int i = 0; i < 100; i++) {
                 i1 = random.nextInt(100);
                writer.println(i1);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

    }

    @Test
    public void textRead01() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Text.dat"));
            String str = null;
            while ((str = reader.readLine()) != null) {
                System.out.println(str);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void binWrite01() {
        try {
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("Random.dat"));
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                int i1 = random.nextInt(100);
                outputStream.write(i1);
            }
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void binRead01() {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new DataInputStream(new FileInputStream("Random.dat")));
            byte[] bytes = new byte[50];

            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                System.out.println(Arrays.toString(bytes));
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void binWriteText01() {
        try {
            String s = "Mary don't like you, give 1232313  舒服的地方";
            byte[] bytes = s.getBytes();
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream("Text.dat"));
            outputStream.write(bytes);
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Test
    public void binReadText01() {
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new DataInputStream(new FileInputStream("Text.dat")));
            byte[] bytes = new byte[5];

            int read;
            while ((read = inputStream.read(bytes)) != -1) {
                System.out.println(new String(bytes, "UTF-8"));
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
