import java.io.*;
import java.util.*;

public class ct1603 {
    static final int DIV = 10_007;
    static int N;
    static int[] arr, dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[] {1, 2, 5};
        dp = new int[N+1];
    }

    static void go() {
        for (int n=0;n<arr.length;n++) dp[arr[n]] = 1;

        for (int i=0;i<=N;i++) {
            for (int n=0;n<arr.length;n++) {
                int num = arr[n];

                if (i - num < 0) continue;
                dp[i] = (dp[i] + dp[i-num]) % DIV;
            }
        }

        System.out.print(dp[N]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
