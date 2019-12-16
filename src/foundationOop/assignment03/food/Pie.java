package foundationOop.assignment03.food;

import foundationOop.assignment03.Menu;
import foundationOop.assignment03.drink.Beer;
import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.drink.WhiteWine;

import java.util.ArrayList;
import java.util.List;

/**
 * Pie object
 */
public class Pie extends Meal {


    @Override
    public Drink drinksRecommendation() {
        List<Drink> drinkList = Menu.getDrinkList();
        for (Drink drink : drinkList) {
            if (drink instanceof Beer) {
                return drink;
            }
        }
        return null;
    }

    /**
     * print Pie in a markdown format
     *
     * @return format string
     */
    public String printf() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("### ").append(getName()).append("       ").append("*").append("£").append(getPrice()).append("*").append(System.lineSeparator())
                .append("ingredients: ");
        for (Ingredient ingredient : getIngredientMap().keySet()) {
            stringBuffer.append(ingredient.getName()).append(", ");
        }
        stringBuffer.replace(stringBuffer.length() - 1, stringBuffer.length(), System.lineSeparator());
        return stringBuffer.toString();
    }


}
