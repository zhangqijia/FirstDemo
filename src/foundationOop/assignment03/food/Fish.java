package foundationOop.assignment03.food;

import foundationOop.assignment03.Menu;
import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.drink.WhiteWine;

import java.util.List;

public class Fish extends Meal {


    @Override
    public Drink drinksRecommendation() {
        List<Drink> drinkList = Menu.getDrinkList();
        for (Drink drink : drinkList) {
            if (drink instanceof WhiteWine) {
                return drink;
            }
        }
        return null;
    }
}
