package drink;

import constant.DrinkType;
import food.Ingredient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * drink is the superclass of all kinds of drinks, such as Beer, Red Wine, White Wine, Soft Drink, cocktail etc..
 */
public abstract class Drink {

    private String name;
    private double price;
    private DrinkType type;
    /**
     * per cup of drink consists of one or more basic wine or soft drinks.
     * and the volume of every part is fixed.
     */
    private Map<Ingredient, Double> ingredientMap = new HashMap<>();

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
        BigDecimal mealPrice = BigDecimal.ZERO;
        // sum all cost of ingredients
        for (Map.Entry<Ingredient, Double> entry : ingredientMap.entrySet()) {
            Ingredient ingredient = entry.getKey();
            Double amount = entry.getValue();
            double cost = ingredient.getCost();
            BigDecimal add = BigDecimal.valueOf(cost).multiply(BigDecimal.valueOf(amount));
            mealPrice = mealPrice.add(add);
        }
        // ceil and keep two fractions
        this.price = mealPrice.setScale(2, BigDecimal.ROUND_CEILING).doubleValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public DrinkType getType() {
        return type;
    }

    public void setType(DrinkType type) {
        this.type = type;
    }

    /**
     * add basic ingredients to a drink's ingredients list
     *
     * @param ingredient ingredient
     * @param amount     the amount of this ingredient
     */
    public void addIngredient(Ingredient ingredient, double amount) {
        ingredientMap.put(ingredient, amount);
    }

    /**
     * get the String consists of all ingredients of this Drink
     *
     * @return String
     */
    public String getIngredientStr() {
        StringBuilder s = new StringBuilder(" ");
        for (Ingredient ingredient : ingredientMap.keySet()) {
            s.append(ingredient.getName()).append(",");
        }
        return s.substring(0, s.length() - 1);
    }
}
