package foundationOop.assignment03.food;

import foundationOop.assignment03.constant.IngredientType;

import java.util.Objects;

/**
 * Ingredients for pie
 */
public class Ingredient {

    private String name;
    private double cost;
    /**
     * use enum to present the type of ingredient
     */
    private IngredientType type;

    public Ingredient() {
    }

    public Ingredient(String name, double cost, IngredientType type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientType getType() {
        return type;
    }

    public void setType(IngredientType type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
