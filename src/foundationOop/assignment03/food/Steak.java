package foundationOop.assignment03.food;

import foundationOop.assignment03.Menu;
import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.drink.RedWine;
import foundationOop.assignment03.drink.WhiteWine;

import java.util.List;

public class Steak extends Meal {

    @Override
    public Drink drinksRecommendation() {
        List<Drink> drinkList = Menu.getDrinkList();
        for (Drink drink : drinkList) {
            if (drink instanceof RedWine) {
                return drink;
            }
        }
        return null;
    }
}
