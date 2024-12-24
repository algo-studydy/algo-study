import java.util.Scanner;

public class Main {
    static int fibCount;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int ans1 = fibonacci(n);
        fib(n);
        System.out.println(fibCount + " " + ans1);
    }

    private static int fibonacci(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = dp[2] = 1;
        int count = 0;
        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            count++;
        }
        return count;
    }

    private static int fib(int n) {
        if( n == 1 || n == 2){
            fibCount++;
            return 1;
        }else{
            return fib(n - 1) + fib(n - 2);
        }
    }
}
