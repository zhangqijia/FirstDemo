package foundationOop.week3;

import java.util.Scanner;

public class KnightAndCoins {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many gold coins do you want ? ");
        int coinsCount = scanner.nextInt();

        int squarCount = 0;
        int coinsGiven = 0;
        // calculate the coin counts and the squares count
        while (coinsGiven < coinsCount) {
            int i = 1 << squarCount;
            coinsGiven += i;
            squarCount++;
        }

        System.out.println("You need " + (squarCount) + "square");
    }
}
