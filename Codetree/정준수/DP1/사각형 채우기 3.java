import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ct1304 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int N = Integer.parseInt(br.readLine());
        int div = 1_000_000_007;
        long[] dp = new long[4];

        dp[0] = 1;
        for (int i=1;i<=N;i++) {
            long[] memo = new long[4];
            memo[0] += (2 * dp[0] + dp[1] + dp[2] + dp[3]) % div;
            memo[1] += (dp[0] + dp[2]) % div;
            memo[2] += (dp[0] + dp[1]) % div;
            memo[3] += (dp[0]) % div;

            dp = memo;
        }

        System.out.print(dp[0]);
    }
}
