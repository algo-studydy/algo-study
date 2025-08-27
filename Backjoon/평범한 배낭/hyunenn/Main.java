
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] weights;
    static int[] values;
    static int[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        weights = new int[N+1];
        values = new int[N+1];
        dp = new int[N+1][K+1];

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=K;j++) {
                if(weights[i] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-weights[i]] + values[i]);
            }
        }

        System.out.println(dp[N][K]);
        /**
         * N=4, K=7
         * 6 13
         * 4 8
         * 3 6
         * 5 12
         *       0 1 2 3 4 5 6 7
         *  6 -> 0 0 0 0 0 0 13 13
         *  4 -> 0 0 0 0 8 8 13 13
         *  3 -> 0 0 0 6 8 8 13 14
         *  5 -> 0 0 0 6 8 12 14 14
         *
         *  1) j가 현재 무게 i보다 작으면 dp[i][j] = dp[i-1][j]
         *  2) j >= i 이면,
         *  dp[i][j] = Math.max(dp[i-1][j], dp[i][j-weight[i]] + weight[i])
         */
    }
}
