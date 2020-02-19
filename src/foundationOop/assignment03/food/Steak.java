package foundationOop.assignment03.food;

import foundationOop.assignment03.constant.DrinkType;
import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.restaurant.Menu;

import java.util.List;

public class Steak extends Meal {

    /**
     * For steak, we always recommend red wine
     *
     * @return foundationOop.assignment03.drink
     */
    @Override
    public Drink drinksRecommendation() {
        List<Drink> drinkList = Menu.getDrinkList();
        for (Drink drink : drinkList) {
            if (DrinkType.RED_WINE.equals(drink.getType())) {
                return drink;
            }
        }
        return null;
    }
}
