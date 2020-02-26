package week2;

public class Fib {

    public static int calculateN(int n) {
        if (n == 1 || n == 2)
            return 1;
        int interval = 0;
        int n0 = 1;
        int n1 = 1;
        for (int i = n - 2; i > 0; i--) {
            interval = n0 + n1;
            n0 = n1;
            n1 = interval;
        }
        return interval;
    }

    public static void main(String[] args) {
        long start;
        long end;
        System.out.println("Iterative...");
        start = System.currentTimeMillis();
        for (int i = 1; i < 40; i++) {
            System.out.println(i + ": " + calculateN(i));
        }
        end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }
}
