package foundationOop.assignment03.food;

import foundationOop.assignment03.constant.DrinkType;
import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.restaurant.Menu;

import java.util.List;

/**
 * Pie object
 */
public class Pie extends Meal {

    /**
     * for pie, we always recommend Beer.
     *
     * @return foundationOop.assignment03.drink
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
