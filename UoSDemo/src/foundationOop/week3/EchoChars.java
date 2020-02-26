package foundationOop.week3;


import java.util.Scanner;

public class EchoChars {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("give you answer");
            // read a string
            java.lang.String input = scanner.next();

            //the length
            int length = input.length();
            System.out.println("length of the String" + length);

            // reverse the String
            String result = "";
            for (int i = length - 1; i >= 0; i--) {
                result += input.charAt(i);
            }
            System.out.println("reversed String:" + result);
        }
    }
}
