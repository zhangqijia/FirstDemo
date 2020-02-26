package week0.exception;

public class Customer {

    private final int HOT_TEMPERATURE = 85;
    private final int COOL_TEMPERTURE = 65;

    public void drinkCoffee(CupCoffee coffee) throws TooHotExceptioin, TooCoolException {
        int temperture = coffee.getTemperture();
        if (temperture > HOT_TEMPERATURE)
            throw new TooHotExceptioin("too hot!!! do you want to kill me!", temperture);
        if (temperture < COOL_TEMPERTURE)
            throw new TooCoolException("too cool!!! I'm going to sue you!");
        else { // enjoy the coffee
            System.out.println("coffer is good!");
            coffee.setContent(0);
        }
    }
}
