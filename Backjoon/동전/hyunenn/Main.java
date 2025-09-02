
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(br.readLine());
            int[] coins = new int[N+1];
            int[][] dp = new int[N+1][K+1];
            for(int i=1;i<=N;i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            // 초기 dp 채우기
            for(int i=0;i<=N;i++) {
                dp[i][0] = 1;
            }
            for(int i=1;i<=K;i++) {
                if(i % coins[1] == 0) {
                    dp[1][i] = 1;
                }
            }

            for(int i=2;i<=N;i++) {
                for(int j=1;j<=K;j++) {
                    if(coins[i] > j) dp[i][j] = dp[i-1][j];
                    else dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]];
                }
            }

            System.out.println(dp[N][K]);
        }
    }
}
