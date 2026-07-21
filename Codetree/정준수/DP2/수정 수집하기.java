import java.io.*;
import java.util.*;

public class ct1805 {
    static int N, K;
    static int[][] arr;
    static int[][][] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1][2];
        dp = new int[N+1][2][K+1];

        String inputString = br.readLine();
        for (int i=1;i<=N;i++) {
            char c = inputString.charAt(i-1);
            if (c == 'L') arr[i][0] = 1;
            else arr[i][1] = 1;
        }
    }

    static void go() {
        for (int i=0;i<=N;i++) {
            for (int j=0;j<2;j++) {
                for (int k=0;k<=K;k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        dp[1][0][K] = arr[1][0];
        dp[1][1][K-1] = arr[1][1];

        int ans = 0;
        for (int i=2;i<=N;i++) {
            for (int j=0;j<2;j++) {
                int val = arr[i][j];

                for (int k=K-i+1;k<=K;k++) {
                    if (k < 0) continue;

                    if (dp[i-1][j][k] != -1) dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][j][k] + val);
                    if (k<K) dp[i][j][k] = Math.max(dp[i][j][k], dp[i-1][(j+1) % 2][k+1] + val);
                    ans = Math.max(ans, dp[i][j][k]);
                }
            }
        }

        System.out.print(ans);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
