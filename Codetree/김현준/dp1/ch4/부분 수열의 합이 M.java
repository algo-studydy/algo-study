
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int N, M;
    static int[] coins;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        coins = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[M + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=0;i<N;i++) {
            int coin = coins[i];
            for(int j=M;j>=coin;j--) {
                if(dp[j-coin] == Integer.MAX_VALUE) continue;
                dp[j] = Math.min(dp[j], dp[j-coin] + 1);
            }
        }

        System.out.println(dp[M] == Integer.MAX_VALUE ? -1 : dp[M]);
    }
}
