package drink;

import constant.DrinkType;

/**
 * Beer class, this class can have its own characteristics, such as flavour, alcohol content.
 */
public class Beer extends Drink {

    /**
     *  assign the drink type of Drink
     */
    public Beer() {
        super.setType(DrinkType.BEER);
    }
}
