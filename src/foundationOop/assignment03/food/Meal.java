package foundationOop.assignment03.food;

import foundationOop.assignment03.constant.IngredientType;
import foundationOop.assignment03.drink.Drink;

import java.util.HashMap;
import java.util.Map;

/**
 * Meal is the superclass of all dishes, such as Pie, Fish, Steak
 */
abstract public class Meal {

    /**
     * the name of meal
     */
    private String name;
    /**
     * the price of meal
     */
    private double price;
    /**
     * the ingredients of meal, the key is the type of ingredients
     * and the value is the amount of pre ingredient
     */
    private Map<Ingredient, Double> ingredientMap = new HashMap<>();

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

    /**
     * add ingredient by recipe to the meal
     *
     * @param ingredient ingredient
     * @param amount     the amount of this ingredient
     */
    public void addIngredient(Ingredient ingredient, double amount) {
        ingredientMap.put(ingredient, amount);
    }

    /**
     * is or not vegetarian
     * vegetarian products include vegan products
     *
     * @return true value indicates this pie is vegetarian
     */
    public boolean isVegetarian() {
        for (Ingredient ingredient : ingredientMap.keySet()) {
            // check all ingredients of this meal, if any one ingredients is not vegan or vegetarian, return false
            if (IngredientType.MEAT.equals(ingredient.getType())) {
                return false;
            }
        }
        return true;
    }

    /**
     * is or not vegan
     *
     * @return true value indicates this pie is vegan
     */
    public boolean isVegan() {
        for (Ingredient ingredient : ingredientMap.keySet()) {
            // check all ingredients of this meal, if any one ingredients is not vegan, return false
            if (!IngredientType.VEGAN.equals(ingredient.getType())) {
                return false;
            }
        }
        return true;
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
     * add 20% cost for staff salary
     * and the price ceil and keep two fractions.
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
        // add a 20% markup to cover staff costs.
        double v = mealPrice * 1.2;
        // ceil and keep two decimal fractions
        v = Math.ceil(v * 100) / 100;
        this.price = v;
    }

    /**
     * check whether the pie uses this ingredient
     *
     * @param ingredient the name of ingredient
     * @return true: contains this ingredient
     */
    public boolean contains(String ingredient) {
        for (Ingredient raw : ingredientMap.keySet()) {
            String name = raw.getName();
            if (name.equals(ingredient)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the recommended drink based on the type of meal.
     */
    public abstract Drink drinksRecommendation();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String printName = String.format("%40s", this.getName());
        builder.append("|           ").append("Name:").append("            |")
                .append(printName).append("|").append("\n");
        builder.append("+---------------------------------------------------------------------+\n");
        printName = "         Ingredients:       ";
        for (Ingredient ingredient : ingredientMap.keySet()) {
            String ingredientName = String.format("%40s", ingredient.getName());
            builder.append("|").append(printName).append("|")
                    .append(ingredientName).append("|\n");
            printName = String.format("%28s", "");
        }
        builder.append("+---------------------------------------------------------------------+\n");
        String isVegetarian = String.format("%40b", isVegetarian());
        builder.append("|       IsVegetarian:        |").append(isVegetarian).append("|\n");
        String isVegan = String.format("%40b", isVegan());
        builder.append("|       IsVegan:             |").append(isVegan).append("|");
        return builder.toString();
    }

    public String getIngredientStr() {
        StringBuilder s = new StringBuilder(" ");
        for (Ingredient ingredient : ingredientMap.keySet()) {
            s.append(ingredient.getName()).append(",");
        }
        return s.substring(0, s.length() - 1);
    }


}
