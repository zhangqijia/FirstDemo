package foundationOop.assignment03.drink;

import foundationOop.assignment03.constant.DrinkType;

/**
 * Beer class, this class can have its own characteristics, such as flavour, alcohol content.
 */
public class Beer extends Drink {

    /**
     *  assign the foundationOop.assignment03.drink type of Drink
     */
    public Beer() {
        super.setType(DrinkType.BEER);
    }
}
