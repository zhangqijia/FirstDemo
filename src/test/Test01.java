package test;

import java.math.BigDecimal;

/**
 * USER: ZQJ
 * DATE: 3/5/2020
 * TIME: 11:19 AM
 */
public class Test01 {

    public static void main(String[] args) {
        BigDecimal bigDecimal1 = new BigDecimal("650000000.0");
        BigDecimal bigDecimal2 = new BigDecimal("650000000.00");
        System.out.println(bigDecimal1.equals(bigDecimal2));
    }
}
