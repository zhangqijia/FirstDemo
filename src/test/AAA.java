package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AAA {
    public static void main(String[] args) {

        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> objects1 = new LinkedList<>();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            arrayList.add(new String());
        }
        long l1 = System.currentTimeMillis();
        System.out.println(l1 - l);
        long l2 = System.currentTimeMillis();
        for (int i = 0; i < 20000000; i++) {
            objects1.add(new String());
        }
        long l3 = System.currentTimeMillis();
        System.out.println(l3 - l2);

    }

    public <T> T getElement(List<T> list) {

        return null;
    }

}
