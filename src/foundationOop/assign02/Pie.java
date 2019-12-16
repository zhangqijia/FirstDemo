package foundationOop.assign02;

import java.util.ArrayList;

/**
 * Pie object
 */
public class Pie {

    private String name;
    private double price;
    private ArrayList<Ingredient> ingredients;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Pie() {
    }

    public Pie(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Pie(String name, double price, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    /**
     * is or not vegetarian
     *
     * @return true value indicates this pie is vegetarian
     */
    public boolean isVegetarian() {
        for (Ingredient ingredient : ingredients) {
            // if one ingredients is not vegetarian, return false
            if (!ingredient.isVegetarian()) {
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
        for (Ingredient ingredient : ingredients) {
            // if one ingredients is not vegetarian, return false
            if (!ingredient.isVegan()) {
                return false;
            }
        }
        return true;
    }

    /**
     * check whether the pie uses this ingredient
     *
     * @param ingredient the name of ingredient
     * @return true: contains this ingredient
     */
    public boolean contains(String ingredient) {
        for (Ingredient raw : ingredients) {
            String name = raw.getName();
            if (name.equals(ingredient)) {
                return true;
            }
        }
        return false;
    }

    /**
     * print Pie in a markdown format
     *
     * @return format string
     */
    public String printf() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("### ").append(name).append("       ").append("*").append("£").append(price).append("*").append(System.lineSeparator())
                .append("ingredients: ");
        for (Ingredient ingredient : ingredients) {
            stringBuffer.append(ingredient.getName()).append(", ");
        }
        stringBuffer.replace(stringBuffer.length() - 1, stringBuffer.length(), System.lineSeparator());
        return stringBuffer.toString();
    }
}