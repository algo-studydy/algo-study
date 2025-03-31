import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 동전2 {
    static int N, M;
    static int[] coins;
    static int[] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        coins = new int[N];
        dp = new int[M+1];
        for(int i=0;i<N;i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(coins);
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i=0;i<coins.length;i++) {
            for(int j=1;j<=M;j++) {
                if(j-coins[i] >= 0 && dp[j-coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j - coins[i]] + 1, dp[j]);
            }
        }

//        for(int i : dp) {
//            System.out.print(i + " ");
//        }

        if(dp[M] == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dp[M]);

    }
}
