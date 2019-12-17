package foundationOop.assignment03;

import foundationOop.assignment03.constant.IngredientType;
import foundationOop.assignment03.drink.*;
import foundationOop.assignment03.food.*;
import sheffield.EasyReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * create today's menu by recipe.
 */
public class Menu {

    /**
     * pie list
     */
    private static List<Meal> pieList = new ArrayList<>();
    /**
     * fish list
     */
    private static List<Meal> fishList = new ArrayList<>();
    /**
     * steak list
     */
    private static List<Meal> steakList = new ArrayList<>();
    /**
     * drink list
     */
    private static List<Drink> drinkList = new ArrayList<>();

    /**
     * print all menu in the console
     */
    public void display() {
        System.err.print("Opening! The menu is below");
        for (int i = 0; i < 11; i++) {
            try {
                System.err.print(".");
                if (i == 10)
                    System.out.println();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // the line used to separate contents
        String separatorLine = "+----------------------------------------+----------+--------------------------------------------------+";
        System.out.println(separatorLine);
        // justify every string to a formatted string
        String rightJustified = "|%40s|%10s|%50s|";

        String format = String.format(rightJustified, "", "price", "ingredients");
        System.out.println(format + "\n" + separatorLine);

        // 1. print pies
        format = String.format(rightJustified, "Pies ************************", "------", "------");
        System.out.println(format + "\n" + separatorLine);

        StringBuilder vegetarianPieStr = new StringBuilder();
        StringBuilder veganPieStr = new StringBuilder();
        for (Meal meal : pieList) {
            format = String.format(rightJustified, meal.getName(), "£" + meal.getPrice(), meal.getIngredientStr());
            format = format + "\n" + separatorLine;
            if (meal.isVegetarian()) {
                vegetarianPieStr.append(format).append("\n");
                if (meal.isVegan()) {
                    veganPieStr.append(format).append("\n");
                }
            } else {
                // 1.1 print every common pie.
                System.out.println(format);
            }
        }
        // 1.2 print vegetarian pies
        format = String.format(rightJustified, "Vegetarian Pies ************************", "------", "------");
        System.out.println(format + "\n" + separatorLine);
        System.out.print(vegetarianPieStr);
        // 1.3 print vegan pies
        format = String.format(rightJustified, "Vegan Pies ************************", "------", "------");
        System.out.println(format + "\n" + separatorLine);
        System.out.print(veganPieStr);
        //2. print fish list
        printOneTypeMealList(separatorLine, rightJustified, "Fishes ************************", fishList);
        //3. print steak list
        printOneTypeMealList(separatorLine, rightJustified, "Steak ************************", steakList);
        //4. print drink list
        printDrinkList(separatorLine, rightJustified, "Drinks ************************", drinkList);

    }

    /**
     * print one type of meal list;
     * because pies should be divided by different type, this method cannot be used to pies.
     *
     * @param separatorLine  ---
     * @param rightJustified formatTemplate
     * @param typeTitle      meal type title
     * @param mealList       meal list
     */
    private void printOneTypeMealList(String separatorLine, String rightJustified, String typeTitle, List<Meal> mealList) {
        String format;
        format = String.format(rightJustified, typeTitle, "------", "------");
        System.out.println(format + "\n" + separatorLine);
        for (Meal meal : mealList) {
            format = String.format(rightJustified, meal.getName(), "£" + meal.getPrice(), meal.getIngredientStr());
            format = format + "\n" + separatorLine;
            //print every meal.
            System.out.println(format);
        }
    }

    private void printDrinkList(String separatorLine, String rightJustified, String typeTitle, List<Drink> drinkList) {
        String format;
        format = String.format(rightJustified, typeTitle, "------", "------");
        System.out.println(format + "\n" + separatorLine);
        for (Drink drink : drinkList) {
            format = String.format(rightJustified, drink.getName(), "£" + drink.getPrice(), drink.getIngredientStr());
            format = format + "\n" + separatorLine;
            //print every meal.
            System.out.println(format);
        }
    }

    /**
     * load ingredients into Stock.
     * and load today's recipes.
     */
    public void load() {
        System.out.println("Welcome! Our restaurant is preparing... Please wait a moment...");
        EasyReader ingredientReader = new EasyReader("C:\\Users\\ZhangQijia\\IdeaProjects\\FirstDemo\\src\\foundationOop\\assignment03\\recipe\\ingredientList.txt");
        EasyReader pieReader = new EasyReader("C:\\Users\\ZhangQijia\\IdeaProjects\\FirstDemo\\src\\foundationOop\\assignment03\\recipe\\pies.txt");
        EasyReader fishReader = new EasyReader("C:\\Users\\ZhangQijia\\IdeaProjects\\FirstDemo\\src\\foundationOop\\assignment03\\recipe\\fish.txt");
        EasyReader steakReader = new EasyReader("C:\\Users\\ZhangQijia\\IdeaProjects\\FirstDemo\\src\\foundationOop\\assignment03\\recipe\\steak.txt");
        EasyReader drinkReader = new EasyReader("C:\\Users\\ZhangQijia\\IdeaProjects\\FirstDemo\\src\\foundationOop\\assignment03\\recipe\\drinks.txt");
        // save ingredients into stock
        while (!ingredientReader.eof()) {
            String s = ingredientReader.readString();
            if (s.equals("name:")) {
                this.readIngredient(ingredientReader);
            }
        }
        loadRecipesByType(pieReader, pieList, "pie");
        loadRecipesByType(fishReader, fishList, "fish");
        loadRecipesByType(steakReader, steakList, "steak");
        try {
            loadDrinkList(drinkReader);
            ingredientReader.close();
            pieReader.close();
            fishReader.close();
            steakReader.close();
            drinkReader.close();
        } catch (IOException e) {
            System.err.println("close inputStream error");
            e.printStackTrace();
        }
    }

    /**
     * load drinks from drink list file.
     *
     * @param drinkReader the input stream
     */
    private void loadDrinkList(EasyReader drinkReader) {
        while (!drinkReader.eof()) {
            Drink drink = null;
            // name
            String name = drinkReader.readString();
            if ("name:".equals(name)) {
                name = drinkReader.readString();
            }
            if (name.contains("Beer")) {
                drink = new Beer();
            } else if (name.contains("White Wine")) {
                drink = new WhiteWine();
            } else if (name.contains("Red Wine")) {
                drink = new RedWine();
            } else {
                drink = new SoftDrink();
            }
            drink.setName(name);

            // ingredients
            drinkReader.readString();
            boolean reading = true;
            while (reading) {
                String s = drinkReader.readString();
                if (s.equals("name:")) {
                    drinkList.add(drink);
                    reading = false;
                } else {
                    int i = s.lastIndexOf(" ");
                    double weight = 0;
                    weight = Double.parseDouble(s.substring(i + 1).replace("kg", ""));
                    String ingredientName = s.substring(0, i);
                                /*find this ingredient from stock
                                you can't get ingredient's detail from recipes,
                                you should find it from Stock List
                                 */
                    Ingredient ingredient = Stock.searchIngredientDetail(ingredientName);
                    drink.addIngredient(ingredient, weight);
                }
                if (drinkReader.eof()) {
                    drinkList.add(drink);
                    reading = false;
                }
            }
        }
    }

    /**
     * load different kinds of meal recipes to menu's meal list based on their types.
     *
     * @param mealReader the fileReader
     * @param mealList   the meal list in menu
     * @param type       the meal type
     */
    private void loadRecipesByType(EasyReader mealReader, List<Meal> mealList, String type) {
        while (!mealReader.eof()) {
            Meal meal = null;

            // name
            String name = mealReader.readString();
            if ("name:".equals(name)) {
                name = mealReader.readString();
            }
            if (type.equals("pie")) {
                meal = new Pie();
            } else if (type.equals("fish")) {
                meal = new Fish();
            } else if (type.equals("steak")) {
                meal = new Steak();
            }
            meal.setName(name);
            if (name.equals("Chicken and Mushroom")) {
                System.out.println("---");
            }
            // ingredients
            mealReader.readString();
            boolean reading = true;
            while (reading) {
                String s = mealReader.readString();
                if (s.equals("name:")) {
                    mealList.add(meal);
                    reading = false;
                } else {
                    int i = s.lastIndexOf(" ");
                    double weight = 0;
                    weight = Double.parseDouble(s.substring(i + 1).replace("kg", ""));
                    String ingredientName = s.substring(0, i);
                                /*find this ingredient from stock
                                you can't get ingredient's detail from recipes,
                                you should find it from Stock List
                                 */
                    Ingredient ingredient = Stock.searchIngredientDetail(ingredientName);
                    meal.addIngredient(ingredient, weight);
                }
                if (mealReader.eof()) {
                    mealList.add(meal);
                    reading = false;
                }
            }
        }
    }

    /**
     * encapsulate one ingredient to Stock by reading from file
     *
     * @param easyReader
     */
    private void readIngredient(EasyReader easyReader) {
        String name;
        double cost;
        IngredientType type;
        Ingredient ingredient;
        double amount;
        name = easyReader.readString();
        easyReader.readString();
        String costPerUnit = easyReader.readString();
        cost = Double.parseDouble(costPerUnit.split("/")[0]);
        easyReader.readString();
        String weight = easyReader.readString();
        amount = Double.parseDouble(weight.replace("kg", ""));
        if (name.contains("(vv)")) {
            type = IngredientType.VEGAN;
        } else if (name.contains("(v)")) {
            type = IngredientType.VEGETARIAN;
        } else {
            type = IngredientType.MEAT;
        }
        ingredient = new Ingredient(name, cost, type);
        Stock.addIngredient(ingredient, amount);
    }

    /**
     * get one meal instance by mealName
     *
     * @param mealName mealName
     * @return Meal
     */
    public Meal getMeal(String mealName) {
        for (Meal meal : pieList) {
            if (meal.getName().equals(mealName)) {
                return meal;
            }
        }
        for (Meal meal : steakList) {
            if (meal.getName().equals(mealName)) {
                return meal;
            }
        }
        for (Meal meal : fishList) {
            if (meal.getName().equals(mealName)) {
                return meal;
            }
        }
        return null;
    }

    /**
     * get Drink instance by name
     *
     * @param drinkName drinkname
     * @return drink
     */
    public Drink getDrink(String drinkName) {
        for (Drink drink : drinkList) {
            if (drink.getName().equals(drinkName)) {
                return drink;
            }
        }
        return null;
    }

    public static List<Drink> getDrinkList() {
        return drinkList;
    }


}
