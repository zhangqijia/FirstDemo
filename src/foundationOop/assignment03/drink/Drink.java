package foundationOop.assignment03.drink;

import foundationOop.assignment03.food.Ingredient;

import java.util.HashMap;
import java.util.Map;

/**
 * drink is the superclass of Beer,Red Wine, White Wine, Soft Drink.
 */
public class Drink {

    private String name;
    private double price;
    /**
     * per cup of drink consists of one or more basic wine or soft drinks.
     * and the volume of every part is fixed.
     */
    private Map<Ingredient, Double> ingredientMap = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the price of this meal
     *
     * @return the price of meal
     */
    public double price() {
        // if price has not been assigned
        if (price == 0) {
            this.calculatePrice();
        }
        return price;
    }

    /**
     * calculate the Meal's price based on the ingredients price and amount
     * <p>
     * drink's price does not need to add 20% of ingredients
     */
    private void calculatePrice() {
        double mealPrice = 0;
        // sum all cost of ingredients
        for (Map.Entry<Ingredient, Double> entry : ingredientMap.entrySet()) {
            Ingredient ingredient = entry.getKey();
            Double amount = entry.getValue();
            double cost = ingredient.getCost();
            mealPrice = mealPrice + (cost * amount);
        }
        // ceil and keep two fractions
        this.price = Math.ceil(mealPrice * 100) / 100;
    }

    public double getPrice() {
        // if price has not been assigned
        if (price == 0) {
            this.calculatePrice();
        }
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<Ingredient, Double> getIngredientMap() {
        return ingredientMap;
    }

    public void setIngredientMap(Map<Ingredient, Double> ingredientMap) {
        this.ingredientMap = ingredientMap;
    }

    /**
     * add basic drinks by drink list
     *
     * @param ingredient ingredient
     * @param amount     the amount of this ingredient
     */
    public void addIngredient(Ingredient ingredient, double amount) {
        ingredientMap.put(ingredient, amount);
    }

    public String getIngredientStr() {
        StringBuilder s = new StringBuilder(" ");
        for (Ingredient ingredient : ingredientMap.keySet()) {
            s.append(ingredient.getName()).append(",");
        }
        return s.substring(0, s.length() - 1);
    }
}
