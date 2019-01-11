import java.util.*;

class Primes {
    public static void main(String[] args) {
        int i1=Integer.parseInt(args[0]);
        int i2=Integer.parseInt(args[1]);
        for (int i = i1; i <= i2; i++) {
            for (int j = 2; j <= Math.ceil(Math.sqrt(i2)) && j <= i; j++) {
                if (i % j == 0 && i != 2) {
                    break;
                }
                if (j == Math.ceil(Math.sqrt(i2)) && i != 1 || j == i - 1 || i ==2) {
                    System.out.println(i);
                    break;
                }

            }
        }

    }
}
