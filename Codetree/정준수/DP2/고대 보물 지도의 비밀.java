import java.io.*;
import java.util.*;

public class ct1704 {
    static int N, K;
    static int[] arr;
    static int[][] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new int[N+1][K+1];

        for (int[] dpt : dp) Arrays.fill(dpt, -1_000_000_000);

        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void go() {
        int max = Integer.MIN_VALUE;
        for (int i=1;i<=N;i++) {
            int num = arr[i];

            if (num < 0) {
                for (int k=0;k<K;k++) {
                    if (dp[i-1][k+1] + num > num) dp[i][k] = dp[i-1][k+1] + num;
                    else dp[i][k] = num;
                }
            } else {
                for (int k=0;k<=K;k++) {
                    dp[i][k] = Math.max(num, dp[i-1][k] + num);
                }
            }

            for (int k=0;k<=K;k++) {
                max = Math.max(max,dp[i][k]);
            }
        }

        System.out.print(max);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}


