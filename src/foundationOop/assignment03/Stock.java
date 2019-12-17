package foundationOop.assignment03;

import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.food.Ingredient;
import foundationOop.assignment03.food.Meal;

import java.util.HashMap;
import java.util.Map;

/**
 * the stock of restaurant ingredients
 */
public class Stock {

    /**
     * every chef of this restaurant uses one stock.
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
     * check whether restaurant has enough ingredients for this meal
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
     * check whether restaurant has enough ingredients for this drink
     *
     * @param drink      drink instance
     * @param drinkCount the count of drink
     * @return whether the ingredient of this drink is enough
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
     * @param drink      drink
     * @param drinkCount ordered count of drink
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
            Ingredient key = entry.getKey();
            Double value = entry.getValue();
            Double stockAmount = ingredientStock.get(key);
            stockAmount = stockAmount - value * count;
            ingredientStock.put(key, stockAmount);
        }
    }

    /**
     * print out the detail of Stock
     */
    public static void printf() {
        System.out.println("Today's Stock detail:");
        ingredientStock.forEach((ingredient, amount) -> {
            System.out.print(ingredient.getName());
            for (int i = 0; i < 7; i++) {
                try {
                    System.out.print("-");
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(amount + "kg");
        });
    }
}