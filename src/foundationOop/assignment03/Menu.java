package foundationOop.assignment03;

import foundationOop.assignment03.constant.IngredientType;
import foundationOop.assignment03.drink.*;
import foundationOop.assignment03.food.*;
import foundationOop.assignment03.tool.PrintTools;
import sheffield.EasyReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
        // create a loading animation
        PrintTools.loadingAnimation(".", 150L, 10);
        System.out.println();
        // the line used to separate contents
        String separatorLine = "+----------------------------------------+----------+--------------------------------------------------+";
        System.out.println(separatorLine);
        // justify every string to a formatted string
        String rightJustified = "|%40s|%10s|%50s|";

        String format = String.format(rightJustified, "", "price", "ingredients");
        System.out.println(format + "\n" + separatorLine);

        // 1. print pies
        format = String.format(rightJustified, "Pies ************************", "******", "******");
        System.out.println(format + "\n" + separatorLine);
        // firstly, print common pies
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
        PrintTools.loadingAnimation(null, 150L, 8);
        // 1.2 print vegetarian pies
        format = String.format(rightJustified, "Vegetarian Pies ************************", "******", "******");
        System.out.println(format + "\n" + separatorLine);
        System.out.print(vegetarianPieStr);
        PrintTools.loadingAnimation(null, 150L, 6);
        // 1.3 print vegan pies
        format = String.format(rightJustified, "Vegan Pies ************************", "******", "******");
        System.out.println(format + "\n" + separatorLine);
        System.out.print(veganPieStr);
        PrintTools.loadingAnimation(null, 150L, 6);
        //2. print fish list
        printOneTypeMealList(separatorLine, rightJustified, "Fishes ************************", fishList);
        PrintTools.loadingAnimation(null, 150L, 8);
        //3. print steak list
        printOneTypeMealList(separatorLine, rightJustified, "Steak ************************", steakList);
        PrintTools.loadingAnimation(null, 150L, 8);
        //4. print drink list
        printDrinkList(separatorLine, rightJustified, "Drinks ************************", drinkList);
        PrintTools.loadingAnimation(null, 150L, 8);

    }

    /**
     * load ingredients into Stock.
     * and load today's recipes into Menu.
     */
    public void load() {
        System.out.println("Welcome! Our restaurant is preparing... Please wait a moment...");
        String path = "C:\\Users\\ZhangQijia\\IdeaProjects\\FirstDemo\\src\\foundationOop\\assignment03\\";
        // create readers to read all files
        EasyReader ingredientReader = new EasyReader(path + "/recipe/ingredientList.txt");
        EasyReader pieReader = new EasyReader(path + "/recipe/pies.txt");
        EasyReader fishReader = new EasyReader(path + "/recipe/fish.txt");
        EasyReader steakReader = new EasyReader(path + "/recipe/steak.txt");
        EasyReader drinkReader = new EasyReader(path + "/recipe/drinks.txt");
        // save ingredients into stock
        while (!ingredientReader.eof()) {
            String s = ingredientReader.readString();
            if (s.equals("name:")) {
                // "name" String means a start of a ingredient, so read the detail of this ingredient and add it into Stock
                this.readIngredient(ingredientReader);
            }
        }
        // load all meals and drinks into Menu instance fields.
        loadRecipesByType(pieReader, pieList, "pie");
        loadRecipesByType(fishReader, fishList, "fish");
        loadRecipesByType(steakReader, steakList, "steak");
        loadDrinkList(drinkReader);
        try {
            // close all reader stream
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
            // read name
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

            // read ingredients of drink
            drinkReader.readString();
            // declare a boolean to control the loop for reading ingredients
            boolean reading = true;
            while (reading) {
                String s = drinkReader.readString();
                // when program reads another "name", that means another start of a meal, so end this loop.
                if (s.equals("name:")) {
                    drinkList.add(drink);
                    reading = false;
                } else {
                    int i = s.lastIndexOf(" ");
                    double weight = 0;
                    weight = Double.parseDouble(s.substring(i + 1).replace("kg", ""));
                    String ingredientName = s.substring(0, i);
                    Ingredient ingredient = Stock.searchIngredientDetail(ingredientName);
                    drink.addIngredient(ingredient, weight);
                }
                // if it is the end of file, stop this loop.
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
            // read name
            String name = mealReader.readString();
            if ("name:".equals(name)) {
                name = mealReader.readString();
            }
            /* the superclass of these kinds of meals are the same one.
             * So, meal can be assigned to Pie, Fish, Steak's reference.
             */
            if (type.equals("pie")) {
                meal = new Pie();
            } else if (type.equals("fish")) {
                meal = new Fish();
            } else if (type.equals("steak")) {
                meal = new Steak();
            }
            meal.setName(name);
            // read ingredients
            mealReader.readString();
            // declare a boolean to control the loop for reading ingredients
            boolean reading = true;
            while (reading) {
                // when read another "name" String, that means another start of a meal, so end this loop.
                String s = mealReader.readString();
                if (s.equals("name:")) {
                    mealList.add(meal);
                    reading = false;
                } else {
                    // get ingredient's name and quantity
                    int i = s.lastIndexOf(" ");
                    double weight = 0;
                    weight = Double.parseDouble(s.substring(i + 1).replace("kg", ""));
                    String ingredientName = s.substring(0, i);
                    /* find this ingredient from stock, because you can't get ingredient's detail from recipes.
                     *  You should find it from Stock's ingredients List
                     */
                    Ingredient ingredient = Stock.searchIngredientDetail(ingredientName);
                    // save this ingredient into meal instance
                    meal.addIngredient(ingredient, weight);
                }
                // if it is the end of file, stop this loop.
                if (mealReader.eof()) {
                    mealList.add(meal);
                    reading = false;
                }
            }
        }
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
        format = String.format(rightJustified, typeTitle, "******", "******");
        System.out.println(format + "\n" + separatorLine);
        for (Meal meal : mealList) {
            format = String.format(rightJustified, meal.getName(), "£" + meal.getPrice(), meal.getIngredientStr());
            format = format + "\n" + separatorLine;
            //print every meal.
            System.out.println(format);
        }
    }

    /**
     * print drink list;
     *
     * @param separatorLine  ---
     * @param rightJustified formatTemplate
     * @param typeTitle      drink type title，if you want to print drinks into different type, assign this variable.
     * @param drinkList      drink list
     */
    private void printDrinkList(String separatorLine, String rightJustified, String typeTitle, List<Drink> drinkList) {
        String format;
        format = String.format(rightJustified, typeTitle, "******", "******");
        System.out.println(format + "\n" + separatorLine);
        for (Drink drink : drinkList) {
            format = String.format(rightJustified, drink.getName(), "£" + drink.getPrice(), drink.getIngredientStr());
            format = format + "\n" + separatorLine;
            //print every meal.
            System.out.println(format);
        }
    }

    /**
     * encapsulate one ingredient to Stock by reading contents from file
     *
     * @param easyReader the input stream
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
        // judge the ingredient's type
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

    /**
     * get Drink list in Menu
     *
     * @return drink list
     */
    public static List<Drink> getDrinkList() {
        return drinkList;
    }


}
