import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        int Y = sc.nextInt();
        int D = Y - X;

        long k = 0;
        while (k * k <= D) {
            k++;
        }
        k--;

        if (D == 0) {
            System.out.println(0);
            return;
        }

        if (D == k * k) {
            System.out.println(2 * k - 1);
        } else if (D <= k * k + k) {
            System.out.println(2 * k);
        } else {
            System.out.println(2 * k + 1);
        }
    }
}
