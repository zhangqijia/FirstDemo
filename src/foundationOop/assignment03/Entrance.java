package foundationOop.assignment03;

import foundationOop.assignment03.constant.IngredientType;
import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.food.Ingredient;
import foundationOop.assignment03.food.Meal;
import foundationOop.assignment03.food.Pie;
import sheffield.EasyReader;

public class Entrance {
    public static void main(String[] args) {

        EasyReader easyReader = new EasyReader();

        Menu menu = new Menu();

        //0. load today's ingredients and recipes
        menu.load();

        //1. print whole menu
        menu.display();

        // the process of shopping starts
        while (true) {
            //2. prompt what meal do you want to order
            Meal meal = null;
            int mealCount = 0;
            boolean ordering = true;
            while (ordering) {
                String mealName = easyReader.readString("What would you like to order? please type the name of one meal.\n");
                meal = menu.getMeal(mealName);
                if (meal == null) {
                    System.err.println("Sorry, we don't have this meal. Please check the menu.");
                } else {
                    // 2.1 the count of meal
                    mealCount = easyReader.readInt("Got it! Then how many meals do you want?\n");
                    // 2.3 check whether we have enough stock
                    boolean mealAvailable = Stock.checkMealIngredients(meal, mealCount);
                    if (!mealAvailable) {
                        System.err.println("sorry this meal does not have enough stock, please choose other meal");
                    } else {
                        ordering = false;
                    }
                }
            }

            //3. recommend some drink
            System.err.println("Would you like some drinks?");
            Drink drinkRecommendation = meal.drinksRecommendation();
            if (drinkRecommendation != null) {
                System.err.println("We highly recommend \"" + drinkRecommendation.getName() + "\" to you!~~");
            }
            System.err.println("What do you think? ");
            //3.1 order a drink
            Drink drink = null;
            int drinkCount = 0;
            boolean orderingDrink = true;
            while (orderingDrink) {
                String drinkName = easyReader.readString("Please type in the name of drink you want? \n");
                drink = menu.getDrink(drinkName);
                if (drink == null) {
                    System.err.println("Sorry, we don't have this drink. Please check the menu.");
                } else {
                    //3.2 ensure the count of drink
                    drinkCount = easyReader.readInt("Got it! Then how many cups of drink do you want? \n");
                    //3.3 check whether we have enough stock
                    boolean available = Stock.checkDrinnkIngredients(drink, drinkCount);
                    if (!available) {
                        System.err.println("sorry this drink does not have enough stock, please choose other drink");
                    } else {
                        orderingDrink = false;
                    }
                }
            }

            //5. show the price
            System.err.println("You ordered successfully!!! Please pay and get you MealBox!!!");
            double price = meal.price() * mealCount + drink.price() * drinkCount;
            System.err.println("The price of your order is: " + price);
            //6. pay or not?
            String pay = easyReader.readString("Would you like to pay it? (yes/no) \n");
            if ("yes".equals(pay)) {
                System.err.println("Pay successfully, your meal is preparing!");
                // reduce the ingredients stock after customers pay successfully
                Stock.reduceIngredients(meal, mealCount, drink, drinkCount);
            } else {
                System.err.println("Thanks for your visiting!");
            }
            //7. thanks for you order, continue shopping or exit.
            String fin = easyReader.readString("Continuing shopping or exit? \n");
            if ("exit".equals(fin)) {
                System.exit(0);
            }
        }
    }
}
