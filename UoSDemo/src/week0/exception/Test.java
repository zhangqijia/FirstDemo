package week0.exception;

public class Test {

    public static void main(String[] args) {
        int tem = 0;
        if (args.length > 0) {
            try {
                tem = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("give me a int");
                System.exit(0);
            }
            Customer customer = new Customer();
            ICCafe.serveCustomer(customer, tem);
        } else {
            System.err.println("give me a parameter");
        }

    }
}
