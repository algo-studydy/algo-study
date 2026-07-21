import java.io.*;
import java.util.*;

public class ct1607 {
    static int N;
    static int[] arr;
    static int[] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        Arrays.fill(dp, -1);
        for (int i=0;i<N;i++) {
            int v = Integer.parseInt(st.nextToken());
            arr[i+1] = v;
        }
    }

    static void go() {
        dp[0]=0;
        for (int i=1;i<=N;i++) {
            for (int j=1;j<=N;j++) {
                if (i - j < 0) continue;
                if (dp[i-j]==-1) continue;

                dp[i] = Math.max(dp[i], dp[i-j] + arr[j]);
            }
        }

        System.out.print(dp[N]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
