import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N+1][K+1];
        for(int i=0;i<=K;i++) {
            dp[0][i] = 1;
        }
        for(int i=1;i<=N;i++) {
            dp[i][1] = 1;
        }

        for(int i=1;i<=N;i++) {
            for(int j=2;j<=K;j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
            }
        }

        System.out.println(dp[N][K]);
    }
}
