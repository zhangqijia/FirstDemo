package foundationOop.assignment03.restaurant;

import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.food.Ingredient;
import foundationOop.assignment03.food.Meal;
import foundationOop.assignment03.tool.PrintTools;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * the stock of foundationOop.assignment03.restaurant ingredients
 */
public class Stock {

    /**
     * every chef of this foundationOop.assignment03.restaurant uses one stock.
     */
    private static Map<Ingredient, Double> ingredientStock = new HashMap<>();


    /**
     * add a ingredient into stock
     *
     * @param ingredient ingredient
     * @param amount     the amount
     */
    public static void addIngredient(Ingredient ingredient, double amount) {
        Double balance = ingredientStock.get(ingredient);
        // if we have remaining ingredient in stock, add new weight with old
        if (balance == null) {
            balance = amount;
        } else {
            balance = balance + amount;
        }
        ingredientStock.put(ingredient, balance);
    }

    /**
     * find ingredient's detail by name
     *
     * @param ingredientName name of ingredient
     * @return instance of ingredient, if cannot find according ingredient, return null
     */
    public static Ingredient searchIngredientDetail(String ingredientName) {
        for (Ingredient ingredient : ingredientStock.keySet()) {
            if (ingredient.getName().equals(ingredientName)) {
                return ingredient;
            }
        }
        return null;
    }

    /**
     * check whether foundationOop.assignment03.restaurant has enough ingredients for this meal
     *
     * @param meal      the meal ordered
     * @param mealCount the count ordered
     * @return true: have enough ingredients  false: don't have enough ingredients
     */
    public static boolean checkMealIngredients(Meal meal, int mealCount) {
        Map<Ingredient, Double> ingredientMap = meal.getIngredientMap();
        for (Map.Entry<Ingredient, Double> entry : ingredientMap.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double needWeight = entry.getValue() * mealCount;
            Double stock = ingredientStock.get(ingredient);
            if (needWeight > stock) {
                return false;
            }
        }
        return true;
    }

    /**
     * check whether foundationOop.assignment03.restaurant has enough ingredients for this foundationOop.assignment03.drink
     *
     * @param drink      foundationOop.assignment03.drink instance
     * @param drinkCount the count of foundationOop.assignment03.drink
     * @return whether the ingredient of this foundationOop.assignment03.drink is enough
     */
    public static boolean checkDrinkIngredients(Drink drink, int drinkCount) {
        Map<Ingredient, Double> ingredientMap = drink.getIngredientMap();
        for (Map.Entry<Ingredient, Double> entry : ingredientMap.entrySet()) {
            Ingredient ingredient = entry.getKey();
            double needWeight = entry.getValue() * drinkCount;
            Double stock = ingredientStock.get(ingredient);
            if (needWeight > stock) {
                return false;
            }
        }
        return true;
    }

    /**
     * reduce the ingredients amount in stock
     *
     * @param meal       meal
     * @param mealCount  ordered count of meal
     * @param drink      foundationOop.assignment03.drink
     * @param drinkCount ordered count of foundationOop.assignment03.drink
     */
    public static void reduceIngredients(Meal meal, int mealCount, Drink drink, int drinkCount) {
        reduceStockAmount(mealCount, meal.getIngredientMap());
        reduceStockAmount(drinkCount, drink.getIngredientMap());
    }

    /**
     * reduce and update the amount of stock
     *
     * @param count         count
     * @param ingredientMap ingredients list
     */
    private static void reduceStockAmount(int count, Map<Ingredient, Double> ingredientMap) {
        for (Map.Entry<Ingredient, Double> entry : ingredientMap.entrySet()) {
            Ingredient ingredient = entry.getKey();
            Double value = entry.getValue();
            BigDecimal stockAmount = BigDecimal.valueOf(ingredientStock.get(ingredient));
            BigDecimal minuend = BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(count));
            stockAmount = (stockAmount).subtract(minuend);
            ingredientStock.put(ingredient, stockAmount.doubleValue());
        }
    }

    /**
     * print out the detail of Stock
     */
    public static void printf() {
        System.out.println("Today's Stock detail:");
        ingredientStock.forEach((ingredient, amount) -> {
            System.out.print(ingredient.getName());
            // create a loading animation
            PrintTools.loadingAnimation("-", 50L, 6);
            System.out.println(amount + "kg");
        });
    }
}
