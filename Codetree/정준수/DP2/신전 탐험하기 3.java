import java.io.*;
import java.util.*;

public class ct1807 {
    static int N, M;
    static int[][] arr, dp;
    static int ans;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][M+1];
        dp = new int[N+1][M+1];

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1;j<=M;j++) {
                int val = Integer.parseInt(st.nextToken());
                arr[i][j] = val;
            }
        }
    }

    static void go() {
        for (int i=1;i<=M;i++) dp[1][i] = arr[1][i];

        for (int n=2;n<=N;n++) {
            for (int i=1;i<=M;i++) {
                for (int j=1;j<=M;j++) {
                    if (i==j) continue;
                    dp[n][i] = Math.max(dp[n][i], dp[n-1][j] + arr[n][i]);
                }
            }
        }

        for (int i=1;i<=M;i++) ans = Math.max(ans, dp[N][i]);
        System.out.print(ans);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
