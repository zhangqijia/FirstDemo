package foundationOop.week3;

import foundationOop.assignment03.sheffield.EasyReader;

public class SplitManyWords {

    public static void main(String[] args) {
        EasyReader easyReader = new EasyReader("./src/foundationOop/week3/manywords.txt");

        while (true) {
            String s = easyReader.readString();
            if (s == null || s.equals("")) {
                return;
            }
            String[] s1 = s.split("\\s+");
            for (String s2 : s1) {
                System.out.println(s2);
            }
        }
    }
}
