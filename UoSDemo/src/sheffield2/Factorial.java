package sheffield2;

public class Factorial
{
    static int fact(int n)
    {
        if (n == 0)
            return 1;
        else
            return n * fact(n - 1);
    }

    public static void main(String[] args)
    {
        System.out.println("Factorial(4) = " + fact(4));
    }
}
