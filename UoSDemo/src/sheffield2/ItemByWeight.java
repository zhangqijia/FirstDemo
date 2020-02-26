package sheffield2;

public class ItemByWeight extends Item {
    double weight;
    double price;

    public ItemByWeight(String n, double p, double w) {
        super(n, p);
        weight = w;
        this.price = p + 10;
    }

    public double getPrice() {
        return weight * price;
    }

    public String toString() {
        return name + " (" + weight + "kg @ " + price + "£/kg) = £"
                + getPrice();
    }

}
