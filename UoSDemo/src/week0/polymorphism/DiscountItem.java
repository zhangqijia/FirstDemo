package week0.polymorphism;

import sheffield2.Item;

public class DiscountItem extends Item {
    private double discount;

    public DiscountItem(String n, double p, double discount) {
        super(n, p);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        // calculate the number of days before best using date
//        (usingBeforeDate.getTime() - System.currentTimeMillis())/1000
        return discount * super.price;
    }

    public String toString() {
        return name + " = £" + getPrice() + " (DiscountItem saving of £"
                + (1 - discount) * super.price + ")";
    }
}
