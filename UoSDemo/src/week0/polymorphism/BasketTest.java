package week0.polymorphism;

import org.junit.Test;
import sheffield2.Basket;
import sheffield2.Item;
import sheffield2.ItemByWeight;
import sheffield2.Multisave;

public class BasketTest {

    /**
     * test polymorphism 01
     */
    public static void main(String[] args) {
        Item computer = new Item("computer", 300);
        DiscountItem surgicalMask = new DiscountItem("surgical mask", 2.0, 0.8);
        ItemByWeight bananas = new ItemByWeight("bananas", 0.4, 0.7);
        Multisave beer = new Multisave("beer", 1.0, 4, 0.75);
        Item[] it = new Item[4];
        it[0] = surgicalMask;
        it[1] = bananas;
        it[2] = beer;
        it[3] = computer;
        Basket basket = new Basket(it);
        System.out.println(basket.bill());
    }


    /**
     * test polymorphism 02
     */
    @Test
    public void test01() {
        Item[] it = {new Item("computer", 300),
                new DiscountItem("surgical mask", 2.0, 0.8),
                new ItemByWeight("bananas", 0.4, 0.7),
                new Multisave("beer", 1.0, 4, 0.75)};
        Basket basket = new Basket(it);
        double numberOfUnits = basket.getAvg();
        System.out.println("the avg price of unit is: " + numberOfUnits);
    }


    /**
     * test polymorphism 03
     */
    @Test
    public void test02() {
        Item[] it = {
                new Item("computer", 3),
                // item by weight will use this.price not super.price
                new ItemByWeight("bananas", 0.4, 0.7),
                new Multisave("beer", 1.0, 4, 0.75)};
        Basket basket = new Basket(it);
        /* the total value is >10, because in ItemByWeight this.price = super.price + 10
        However, the price of Item is what we assign
        */
        System.out.println(basket.bill());
    }


}