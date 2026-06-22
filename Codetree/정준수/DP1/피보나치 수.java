import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1301 {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];

        if (N < 3) {
            System.out.print(1);
            return;
        }
        dp[1]=1;
        dp[2]=1;
        for (int i=3;i<=N;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        System.out.print(dp[N]);
    }
}
