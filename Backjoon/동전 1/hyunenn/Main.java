import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[] coins;
    static int[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N+1];
        for(int i=1;i<=N;i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N+1][K+1];
        for(int i=0;i<=N;i++) {
            dp[i][0] = 1;
        }
        int cnt = 1;
        for(int j=1;j<=K;j++) {
            if(j % coins[1] == 0) dp[1][j] = cnt;
        }
        for(int i=2;i<=N;i++) {
            for(int j=1;j<=K;j++) {
                // 채울 수 없는 자리면,기존의 값으로 대체
                if(coins[i] > j)  dp[i][j] = dp[i-1][j];
                    // 채울 자리면, 누적합으로 갱신
                else dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]];
            }
        }

        System.out.println(dp[N][K]);
    }
    // N개의 종류로 K의 값을 만들면 되는데 중복 상관 x
    // dp[i][k] -> 3개의 종류로 10원을 만든다 가정하면
    //   1 2 3 4 5 6 7 8 9 10
    // 1 1 1 1 1 1 1 1 1 1 1
    // 2 1 2 2 3 3 4 4 5 5 6
    // 5 1 2 2 3 3 5 6 7 8 8 9
}