package food;

import constant.DrinkType;
import drink.Drink;
import restaurant.Menu;

import java.util.List;

public class Steak extends Meal {

    /**
     * For steak, we always recommend red wine
     *
     * @return drink
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
