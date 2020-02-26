package week0.exception;

import java.util.ArrayList;
import java.util.List;

public class ICCafe {

    public static void serveCustomer(Customer customer, int temperture) {
        CupCoffee cupCoffee = new CupCoffee(100, temperture);
        try {
            customer.drinkCoffee(cupCoffee);
        } catch (TooHotExceptioin tooHotExceptioin) {
            System.out.println(tooHotExceptioin.getMessage());
        } catch (TooCoolException tooCoolException) {
            System.out.println(tooCoolException.getMessage());
        }

    }

    public static void serveCustomer(Customer customer, int[] temperture) {
        List<CupCoffee> cupCoffees = new ArrayList<>();
        for (int i = 0; i < temperture.length; i++) {
            CupCoffee cupCoffee = new CupCoffee(100, temperture[i]);
            cupCoffees.add(cupCoffee);
        }

        for (CupCoffee cupCoffee : cupCoffees) {
            try {
                customer.drinkCoffee(cupCoffee);
            } catch (TooHotExceptioin tooHotExceptioin) {
                System.out.println(tooHotExceptioin);
            } catch (TooCoolException tooCoolException) {
                System.out.println(tooCoolException.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        int[] tems = new int[args.length];
        if (args.length > 0) {
            try {
                for (int i = 0; i < args.length; i++) {
                    int tem = Integer.parseInt(args[i]);
                    tems[i] = tem;
                }
            } catch (NumberFormatException e) {
                System.err.println("give me a int");
                System.exit(0);
            }
            Customer customer = new Customer();
            ICCafe.serveCustomer(customer, tems);
        } else {
            System.err.println("give me a parameter");
        }

    }
}
