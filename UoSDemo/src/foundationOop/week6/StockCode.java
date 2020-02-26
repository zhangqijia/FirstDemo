package foundationOop.week6;

import java.util.Scanner;

public class StockCode {

    private String code;

    /**
     * a default constructor that sets the stock code to an appropriate default value.
     */
    public StockCode() {
        code = "ABC-0-1234";
    }

    /**
     * a constructor that creates a stock code from a string which is passed as a parameter.
     *
     * @param s
     */
    public StockCode(String s) {

        this.code = s;

    }

    /**
     * set the code
     *
     * @param s
     */
    public void setCode(String s) {
        this.code = s;

    }

    /**
     * get the code
     *
     * @return
     */
    public String getCode() {
        return code;
    }

    /**
     * compare whether the stock code represented by current instance of theStockCodeclass is equal to another instance
     *
     * @param c
     * @return
     */
    public boolean equals(StockCode c) {
        return c.getCode().equals(code);
    }

    /**
     * valid the input value whether it corresponds to the code
     *
     * @return
     */
    public boolean isValid() {
        boolean flag = false;
        if (code.length() == 10) {
            flag = true;
            String dash = "-";
            for (int i = 0; i < 10; i++) {
                boolean test = false;
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                        char a = code.charAt(i);
                        test = Character.isUpperCase(a);
                        break;
                    case 3:
                    case 5:
                        String b = code.substring(i, i + 1);
                        test = dash.equals(b);
                        break;
                    case 4:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        char c = code.charAt(i);
                        test = Character.isDigit(c);
                        break;
                }
                flag = flag && test;
            }
        }
        return (flag);
    }

    /**
     * displays the value of theStockCodeclass.
     *
     * @return Stock code attributes
     */
    public String toString() {
        return this.getClass().getName() + ": code = " + code;
    }

    /**
     * a main method that acts as a test harness for the class. It displays whether an input from keyboard is avalid stock code
     *
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        StockCode stockCode = new StockCode(next);
        System.out.println(stockCode.isValid());
    }

}