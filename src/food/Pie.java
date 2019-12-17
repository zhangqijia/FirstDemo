package food;

import constant.DrinkType;
import drink.Drink;
import restaurant.Menu;

import java.util.List;

/**
 * Pie object
 */
public class Pie extends Meal {

    /**
     * for pie, we always recommend Beer.
     *
     * @return drink
     */
    @Override
    public Drink drinksRecommendation() {
        List<Drink> drinkList = Menu.getDrinkList();
        for (Drink drink : drinkList) {
            if (DrinkType.BEER.equals(drink.getType())) {
                return drink;
            }
        }
        return null;
    }
}
