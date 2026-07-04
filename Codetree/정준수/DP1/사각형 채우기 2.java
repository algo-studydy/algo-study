import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ct1305 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int N = Integer.parseInt(br.readLine());
        int div = 10_007;
        int[] dp = new int[N<3?3:N+1];

        dp[1]=1;
        dp[2]=3;
        for (int i=3;i<=N;i++) {
            dp[i] = (dp[i-2] + dp[i-2] + dp[i-1]) % div;
        }

        System.out.print(dp[N]);
    }
}

