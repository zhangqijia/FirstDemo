package foundationOop.week8;

public class BSDepositAccount extends BSAccount {

    private double ARP;

    public BSDepositAccount(int accountNum, String name, double balance) {
        super(accountNum, name, balance);
    }

    public BSDepositAccount(int accountNum, String name, double balance, double ARP) {
        super(accountNum, name, balance);
        this.ARP = ARP;
    }

    public void makeDeposit(double v) {

    }

    public double calculateInterest() {
        return 0;
    }

    public void makeWithdrawal(double v) {


    }
}
