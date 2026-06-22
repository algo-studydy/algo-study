import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ct1302 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int div = 10_007;

        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 1;
        for (int i=3;i<=N;i++) {
            dp[i] = (dp[i-2] + dp[i-3]) % div;
        }

        System.out.print(dp[N]);
    }
}
