package test;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * USER: ZQJ
 * DATE: 3/5/2020
 * TIME: 11:19 AM
 */
public class Test01 {

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI);});
            thread.start();
        }
        System.out.println("over");
    }

    @Test
    public void test01() {
        int[][] c = {{1, 2, 3},
                {2, 3, 4},
                {3, 4, 5}
        };
        for (int[] x : c) {
            for (int i : x) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
