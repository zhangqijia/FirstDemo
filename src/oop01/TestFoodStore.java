package oop01;

import java.util.Calendar;

public class TestFoodStore {

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 5);

        FoodStore foodStore = new FoodStore("milk", 10, instance.getTime());




    }
}
