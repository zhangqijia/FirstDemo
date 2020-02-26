package foundationOop.assign02;

/**
 * Ingredients for pie
 */
public class Ingredient {

    private String name;
    /**
     * is vegetarian or not, default value false
     */
    private boolean vegetarian = false;
    /**
     * is vegan or not, default value false
     */
    private boolean vegan = false;

    public Ingredient(String name) {
        this.name = name;
    }

    public Ingredient(String name, boolean vegetarian, boolean vegan) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.vegan = vegan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }
}