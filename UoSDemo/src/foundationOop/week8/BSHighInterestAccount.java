package foundationOop.week8;

public class BSHighInterestAccount extends BSAccount {

    private double ARP;
    private int waitingPeriod;

    public BSHighInterestAccount(int accountNum, String name, double balance) {
        super(accountNum, name, balance);
    }

    public BSHighInterestAccount(int accountNum, String name, double balance, double ARP, int waitingPeriod) {
        super(accountNum, name, balance);
        this.ARP = ARP;
        this.waitingPeriod = waitingPeriod;
    }

    public void makeDeposit(double v) {

    }

    public void makeWithdrawal(double v) {

    }
}
