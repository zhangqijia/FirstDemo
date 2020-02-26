package sheffield2;

import java.io.*;

public class HelloWorld {
    public static void main(String[] args) {
        try {
//            PrintWriter outfile = new PrintWriter(new FileWriter("hello.txt"));
//            outfile.println("Hello world");
//            outfile.close();
            FileReader fileReader = new FileReader("hello.txt");
            char[] bytes = new char[8192];
            int read;
            while ((read = fileReader.read(bytes,0, 8192)) != -1) {
                for (char aByte : bytes) {
                    if (aByte =='\u0000'){
                        break;
                    }
                    System.out.println(aByte);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
}
