package test;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * USER: ZQJ
 * DATE: 3/5/2020
 * TIME: 11:19 AM
 */
public class Test01 {

    public static void main(String[] args) {
       String s = "booo:and:fooo";
        String[] os = s.split("o", 0);
        System.out.println(Arrays.toString(os));
    }
}
