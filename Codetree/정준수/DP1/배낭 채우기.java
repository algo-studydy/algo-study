import java.io.*;
import java.util.*;

public class ct1606 {
    static int N, M;
    static int[][] arr;
    static int[] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        dp = new int[M+1];
        Arrays.fill(dp, -1);
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr[i][0] = w;
            arr[i][1] = v;
        }
    }

    static void go() {
        dp[0] = 0;
        int max = 0;
        for (int i=0;i<N;i++) {
            for (int j=M;j>=0;j--) {
                if (j - arr[i][0] < 0) continue;
                if (dp[j - arr[i][0]] == -1) continue;

                dp[j] = Math.max(dp[j], dp[j - arr[i][0]] + arr[i][1]);
                max = Math.max(max, dp[j]);
            }
        }

        System.out.print(max);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
