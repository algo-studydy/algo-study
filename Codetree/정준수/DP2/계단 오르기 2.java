import java.io.*;
import java.util.*;

public class ct1702 {
    static int N;
    static int[] arr;
    static int[][] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new int[N+1][4];

        st = new StringTokenizer(br.readLine());
        for (int[] dpt: dp) Arrays.fill(dpt, Integer.MIN_VALUE);
        for (int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void go() {
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;
        dp[1][2] = arr[1];

        int max = 0;
        for (int i=2;i<=N;i++) {
            int coin = arr[i];

            dp[i][3] = dp[i-2][3] + coin;
            dp[i][2] = Math.max(dp[i-1][3], dp[i-2][2]) + coin;
            dp[i][1] = Math.max(dp[i-1][2], dp[i-2][1]) + coin;
            dp[i][0] = Math.max(dp[i-1][1], dp[i-2][0]) + coin;
        }
        max = Math.max(max, dp[N][3]);
        max = Math.max(max, dp[N][2]);
        max = Math.max(max, dp[N][1]);
        max = Math.max(max, dp[N][0]);

        System.out.print(max);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}

