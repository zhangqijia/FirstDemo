package sheffield2;

public class Basket {
    Item[] items;

    public Basket(Item[] it) {
        items = it;
    }

    public double total() {
        double tot = 0.0;
        for (int i = 0; i < items.length; i++)
            tot += items[i].getPrice();
        return tot;
    }

    public String bill() {
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < items.length; i++)
            s.append((i + 1) + ": " + items[i] + "\n");
        s.append("\n\nTOTAL: £" + total() + "\n");
        return s.toString();
    }

    public static void main(String[] args) {

        Item[] a = {new Item("baked beans", 0.3),
                new ItemByWeight("bananas", 0.4, 0.7),
                new Multisave("beer", 1.0, 4, 0.75)};
        Basket b = new Basket(a);

        System.out.println(b.bill());
        // System.out.println("Your total bill is: £" + b.total());
    }

    /**
     * Implement a new function in Basket(or ALBasket) to compute the average cost per unit.
     * You will have to create a new function in Item, getNumberOfUnits(),
     * and possibly some of its subclasses to return the number of units (you can assume an ItemByWeightis a single unit)
     */
    public double getAvg() {
        double total = this.total();
        int totalNumOfUnits = 0;
        for (Item item : items) {
            totalNumOfUnits += item.getNumberOfUnits();
        }
        return total / totalNumOfUnits;
    }

}
