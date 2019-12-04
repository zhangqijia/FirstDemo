package foundationOop.week8;

/*
  Written by: YG
  First written: 20/09/07
  Last modified: 20/09/07
*/

public class BSAccountDemo {
    public static void main(String[] args) {

        // current account
        System.out.println("** current account **");
        BSCurrentAccount myBSCurrentAccount =
                new BSCurrentAccount(184396, "George B", 82.87);
        System.out.println(myBSCurrentAccount);

        System.out.println("deposit 10.00 pounds:");
        myBSCurrentAccount.makeDeposit(10.00);
        System.out.println(myBSCurrentAccount);

        System.out.println("withdraw 50.00 pounds:");
        myBSCurrentAccount.makeWithdrawal(50.00);
        System.out.println(myBSCurrentAccount);

        System.out.println("withdraw 50.00 pounds:");
        myBSCurrentAccount.makeWithdrawal(50.00);
        System.out.println(myBSCurrentAccount);
        System.out.println();

        // deposit account
        System.out.println("** deposit account **");
        BSDepositAccount myBSDepositAccount =
                new BSDepositAccount(184038, "George B", 893.21, 0.03);
        System.out.println(myBSDepositAccount);

        System.out.println("deposit 10.00 pounds:");
        myBSDepositAccount.makeDeposit(10.00);
        System.out.println(myBSDepositAccount);

        System.out.println("calculate the interest:");
        double interest = myBSDepositAccount.calculateInterest();
        System.out.println("  interest: " + interest);

        System.out.println("withdraw 500.00 pounds:");
        myBSDepositAccount.makeWithdrawal(500.00);
        System.out.println(myBSDepositAccount);

        System.out.println("withdraw 500.00 pounds:");
        myBSDepositAccount.makeWithdrawal(500.00);
        System.out.println();

        // high interest account
        System.out.println("** high interest account **");
        BSHighInterestAccount myBSHighInterestAccount =
                new BSHighInterestAccount(184280, "George B", 2231.51, 0.06, 60);
        System.out.println(myBSHighInterestAccount);

        System.out.println("deposit 10.00 pounds:");
        myBSHighInterestAccount.makeDeposit(10.00);
        System.out.println(myBSHighInterestAccount);

        System.out.println("withdraw 500.00 pounds:");
        myBSHighInterestAccount.makeWithdrawal(500.00);
        System.out.println(myBSHighInterestAccount);
        System.out.println();
    }
}
