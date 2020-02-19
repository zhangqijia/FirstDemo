package foundationOop.week3;

import foundationOop.assignment03.sheffield.EasyReader;

public class CountAcids {

    public static void main(String[] args) {

        EasyReader easyReader = new EasyReader();

        String s = easyReader.readString("type your DNA");

        if (s.length() > 18){
            System.out.println("This sequence (18 acids) is too long.");
        }

        int a = 0;
        int c = 0;
        int t = 0;
        int g = 0;
        for (int i = 0; i < s.length(); i++) {
            char cha = s.charAt(i);
            switch (cha) {
                case 'A':
                    a++;
                    break;
                case 'C':
                    c++;
                    break;
                case 'T':
                    t++;
                    break;
                case 'G':
                    g++;
                    break;
                default:
                    System.out.println("Please enter a valid DNA sequence.");
                    System.exit(0);
            }
        }
        System.out.printf("A: %d  C: %d  T: %d  G: %d ", a,c,t,g);


    }
}
