import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long[] dp = new long[101];
        dp[0] = 0;
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for(int i = 5 ; i <= 100 ; i++){
            dp[i] = dp[i-3] + dp[i-2];
        }

        int T = sc.nextInt();
        for(int i = 0 ; i < T ; i++){
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }

}
