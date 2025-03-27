import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        // 홀수 불가능
        if (N % 2 == 1) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[2] = 3;

        // 점화식 : dp[n] = dp[n-2]*dp[2] + dp[n-4]*2 ... dp[0]*2
        for (int i = 4; i <= N; i += 2) {
            dp[i] = dp[i - 2] * dp[2];

            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);

    }
}
