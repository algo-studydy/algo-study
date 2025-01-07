import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if( n == 1 ){
            System.out.println("1");
            return;
        } else if ( n == 2 ) {
            System.out.println("2");
            return;
        }

        int a = 1;
        int b = 2;
        int c = 0;
        for(int i = 3; i <= n ; i++){
            c = (a + b) % 15746;
            a = b;
            b = c;
        }

        System.out.println(c);

    }
}
