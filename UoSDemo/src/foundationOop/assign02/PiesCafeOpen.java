package foundationOop.assign02;

/**
 * The entrance of the Pies Cafe
 */
public class PiesCafeOpen {


   /*public static void main(String[] args) {

        // read today's pies list
        System.out.println("please insert today pies file path: ");
        Scanner scanner = new Scanner(System.in);
        String menuPath = scanner.next();

        // format original menu to a nice presentation format
        new foundationOop.assignment03.restaurant.Menu().formatMenu2(menuPath);

        System.out.println("Welcome to Pies Care!!! :) ");
    }*/

public static void main(String[] args) {
        int n = Integer.parseInt("5");
        for (int i = -3*n/2; i <= n; i++) {
            for (int j = -3*n/2; j <= 3*n/2; j++) {

                // inside either diamond or two circles
                if ((Math.abs(i) + Math.abs(j) < n)
                        || ((-n/2-i) * (-n/2-i) + ( n/2-j) * ( n/2-j) <= n*n/2)
                        || ((-n/2-i) * (-n/2-i) + (-n/2-j) * (-n/2-j) <= n*n/2)) {
                    System.out.print("* ");
                }
                else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
    
}
