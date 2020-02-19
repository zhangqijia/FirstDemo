package foundationOop.week4;

import foundationOop.assignment03.sheffield.EasyReader;

public class PrimeNumbers {

    /**
     * Sieve of Eratosthenes
     * 1. create a list of integer from 2 to n
     * 2. initially, let p =2, the smallest prime number
     * 3. enumerate the multiples of p by counting in increment of p from 2p to n and mark them in list.
     * 4. find the first number greater than p in the list that is not marked.
     * if no such number, stop. Otherwise, let p now equal this new number, and repeat from step3
     * 5. when the algorithm terminates, the numbers remaining not marked in the list are all the primes below n.
     *
     * @param args
     */
    public static void main(String[] args) {
        EasyReader reader = new EasyReader();

        int n = reader.readInt("input a number: ");

        int[] markList = new int[n + 1];

        for (int i = 2; i < Math.sqrt(n); i++) {
            Integer integer = markList[i];
            if (integer != 1) {
                int k;
                for (int j = 0; (k = (i * i + i * j)) <= n; j++) {
                    markList[k] = 1;
                }
            }
        }

        for (int i = 2; i < n + 1; i++) {
            if (markList[i] != 1) {
                System.out.print(i + ",");
            }
        }

    }

}


