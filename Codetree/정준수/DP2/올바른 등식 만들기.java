import java.io.*;
import java.util.*;

public class ct1706 {
    static int N, M;
    static int[] arr;
    static long[][] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new long[N+1][40+1];

        for (long[] dpt : dp) Arrays.fill(dpt, -1);

        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
    }

    static void go() {
        dp[1][20+arr[1]] = 0;
        dp[1][20-arr[1]] = 0;
        dp[1][20+arr[1]] += 1;
        dp[1][20-arr[1]] += 1;
        for (int i=2;i<=N;i++) {
            for (int j=0;j<=40;j++) {
                if (j-arr[i]>=0 && dp[i-1][j-arr[i]] != -1) {
                    if (dp[i][j]==-1) dp[i][j] = 0;
                    dp[i][j] += dp[i-1][j-arr[i]];
                }
                if (j+arr[i]<=40 && dp[i-1][j+arr[i]] != -1) {
                    if (dp[i][j]==-1) dp[i][j] = 0;
                    dp[i][j] += dp[i-1][j+arr[i]];
                }
            }
        }

        System.out.print(dp[N][20+M]==-1?0:dp[N][20+M]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}




