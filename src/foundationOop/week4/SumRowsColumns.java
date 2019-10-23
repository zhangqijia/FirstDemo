package foundationOop.week4;

import sheffield.EasyReader;

import java.util.ArrayList;

public class SumRowsColumns {

    public static void main(String[] args) {


        EasyReader easyReader = new EasyReader();
        int dimension = easyReader.readInt("how many dimensions array do you want: ");
        Integer[][] integers = new Integer[dimension][dimension];

        // index of two-dimensional array
        int x = 0;
        int y = 0;

        // put the num into array
        for (int i = 0; i < dimension * dimension; i++) {
            x = i / dimension;
            y = i % dimension;
            integers[x][y] = easyReader.readInt(" give a number: ");
        }
        ArrayList<Integer> column = new ArrayList<>();
        // calculate the sum of every row and print;
        for (int i = 0; i < dimension; i++) {
            int sum = 0;
            int sumColumn = 0;
            for (int j = 0; j < dimension; j++) {
                System.out.print(integers[i][j] + "\t");
                sum += integers[i][j];
                sumColumn += integers[j][i];
                if (j == dimension - 1) {
                    System.out.println(sum);
                    column.add(sumColumn);
                }
            }
        }

        for (Integer integer : column) {
            System.out.print(integer + "\t");
        }

    }
}
