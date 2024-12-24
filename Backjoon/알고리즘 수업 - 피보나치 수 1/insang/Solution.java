import java.util.Scanner;

public class Main {
    static int cnt1 = 1;
    static int cnt2 = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        fib(n);
        fibonacci(n);
        System.out.println(cnt1 + " " + cnt2);
    }

    private static void fibonacci(int n) {
        int[] dp = new int[41];
        dp[1] = dp[2] = 1;

        for(int i = 3; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
            cnt2++;
        }
    }

    private static int fib(int n) {
        if(n == 1 || n == 2) return 1;
        cnt1++;
        return fib(n-1) + fib(n-2);
    }
}
