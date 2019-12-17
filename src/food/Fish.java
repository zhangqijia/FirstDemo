package food;

import constant.DrinkType;
import drink.Drink;
import restaurant.Menu;

import java.util.List;

public class Fish extends Meal {

    /**
     * The recommendation drink of Fish is a kind drink oif WhiteWine
     *
     * @return drink instance
     */
    @Override
    public Drink drinksRecommendation() {
        List<Drink> drinkList = Menu.getDrinkList();
        for (Drink drink : drinkList) {
            if (DrinkType.WHITE_WINE.equals(drink.getType())) {
                return drink;
            }
        }
        return null;
    }
}
