package oop01;

import java.util.Date;
import java.util.Objects;

public class FoodStore {

    private int depositCount;
    private int withdrawCount;
    private int totalDeposit;
    private int totalWithdraw;


    private String name;
    private int amountStored;
    private Date eatingDeadline;

    public FoodStore() {
    }

    public FoodStore(String name, int amountStored, Date eatingDeadline) {
        this.name = name;
        this.amountStored = amountStored;
        this.eatingDeadline = eatingDeadline;
    }

    public int getAmountStored() {
        return amountStored;
    }

    public void setAmountStored(int amountStored) {
        this.amountStored = amountStored;
    }

    public Date getEatingDeadline() {
        return eatingDeadline;
    }

    public void setEatingDeadline(Date eatingDeadline) {
        this.eatingDeadline = eatingDeadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FoodStore{" +
                "name='" + name + '\'' +
                ", amountStored=" + amountStored +
                ", eatingDeadline=" + eatingDeadline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodStore foodStore = (FoodStore) o;
        return amountStored == foodStore.amountStored &&
                Objects.equals(name, foodStore.name) &&
                Objects.equals(eatingDeadline, foodStore.eatingDeadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amountStored, eatingDeadline);
    }
}
