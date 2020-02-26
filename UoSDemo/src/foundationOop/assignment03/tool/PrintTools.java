package foundationOop.assignment03.tool;

import foundationOop.assignment03.drink.Drink;
import foundationOop.assignment03.food.Meal;

import java.math.BigDecimal;

/**
 * tools to print some contents in a easy-read format
 */
public class PrintTools {

    /**
     * create a loading animation to make print more readable
     *
     * @param point    the String which makes up the animation
     * @param interval long type, the interval between every individual String printing
     * @param times    int type, how many times you want the String to be printed
     */
    public static void loadingAnimation(String point, long interval, int times) {
        int count = times + 1;
        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(interval);
                if (point != null && point.length() > 0)
                    System.out.print(point);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * print receipt on the console
     *
     * @param meal       meal ordered
     * @param mealCount  the count of meal
     * @param drink      foundationOop.assignment03.drink ordered
     * @param drinkCount the count of foundationOop.assignment03.drink
     */
    public static void printReceipt(Meal meal, int mealCount, Drink drink, int drinkCount) {
        System.out.print("Receipt printing");
        loadingAnimation(".", 150L, 6);
        System.out.println();
        StringBuilder builder = new StringBuilder();
        // assign fore part and rear part of one line
        String forePart = "|           MEAL:            |";
        String rearPart = String.format("%40s", meal.getName() + " £" + meal.getPrice() + " X " + mealCount);
        builder.append(forePart)
                .append(rearPart).append("|").append("\n");
        // append separator line
        builder.append("+---------------------------------------------------------------------+\n");
        forePart = "           DRINK:           ";
        rearPart = String.format("%40s", drink.getName() + " £" + drink.getPrice() + " X " + drinkCount);
        builder.append("|").append(forePart).append("|")
                .append(rearPart).append("|\n");
        // append separator line
        builder.append("+---------------------------------------------------------------------+\n");
        BigDecimal mealPrice = BigDecimal.valueOf(meal.price()).multiply(BigDecimal.valueOf(mealCount));
        BigDecimal drinkPrice = BigDecimal.valueOf(drink.price()).multiply(BigDecimal.valueOf(drinkCount));
        double value = mealPrice.add(drinkPrice).doubleValue();
        forePart = "|           PRICE:           |";
        rearPart = String.format("%40s", "£" + value);
        builder.append(forePart).append(rearPart).append("|\n");
        System.out.println(builder);
        System.out.println("The price of your order is: £" + value);
    }
}
