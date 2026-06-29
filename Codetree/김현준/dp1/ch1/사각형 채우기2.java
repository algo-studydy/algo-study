import java.util.Scanner;

public class Main {

    static int[] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // Please write your code here.
        dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 3;
        if(n <= 2) {
            System.out.println(dp[n]);
            return;
        }

        for(int i=3;i<=n;i++) {
            dp[i] = (dp[i-1] + (2 * dp[i-2])) % 10007;
        }

        System.out.println(dp[n]);

    }
}