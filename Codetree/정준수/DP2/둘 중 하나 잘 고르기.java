import java.io.*;
import java.util.*;

public class ct1705 {
    static int N;
    static int[][] arr, dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[2*N+1][2];
        dp = new int[2*N+1][N+1];

        for (int[] dpt : dp) Arrays.fill(dpt, Integer.MIN_VALUE);

        for (int i=1;i<=2*N;i++) {
            st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());
            arr[i][0] = red;
            arr[i][1] = blue;
        }
    }

    static void go() {
        dp[0][N] = 0;
        for (int i=1;i<=2*N;i++) dp[i][N] = dp[i-1][N] + arr[i][1];

        for (int i=1;i<=2*N;i++) {
            int red = arr[i][0];
            int blue = arr[i][1];

            for (int j=0;j<N;j++) {
                if (dp[i-1][j+1] == Integer.MIN_VALUE) continue;

                dp[i][j] = Math.max(dp[i-1][j] + blue, dp[i-1][j+1] + red);
            }
        }

        System.out.print(dp[2*N][0]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}


