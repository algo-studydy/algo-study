import java.io.*;
import java.util.*;

public class ct1601 {
    static final int MAX = 10_000;
    static int N, M;
    static int[] arr, dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[MAX+1];

        Arrays.fill(dp, MAX);
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[arr[i]] = 1;
        }
    }

    static void go() {
        for (int i=0;i<=M;i++) {
            for (int j=0;j<N;j++) {
                int coinValue = arr[j];
                int beforeIdx = i - coinValue;

                if (beforeIdx < 0) continue;
                dp[i] = Math.min(dp[i], dp[beforeIdx] + 1);
            }
        }

        System.out.print(dp[M] == MAX ? -1 : dp[M]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
