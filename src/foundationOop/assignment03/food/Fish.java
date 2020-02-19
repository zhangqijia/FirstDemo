package foundationOop.assignment03.food;

import foundationOop.assignment03.constant.DrinkType;
import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.restaurant.Menu;

import java.util.List;

public class Fish extends Meal {

    /**
     * The recommendation foundationOop.assignment03.drink of Fish is a kind foundationOop.assignment03.drink oif WhiteWine
     *
     * @return foundationOop.assignment03.drink instance
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
