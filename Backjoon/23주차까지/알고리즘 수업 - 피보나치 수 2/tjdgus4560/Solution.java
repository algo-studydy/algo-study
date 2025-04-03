import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int a = 1;
        int b = 1;
        int c;
        for( int i = 3; i <= n; i++ ) {
            c = (a + b)%1_000_000_007;
            a = b;
            b = c;
        }
        System.out.println(b + " " + (n-2));
    }




}
