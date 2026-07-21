import java.io.*;
import java.util.*;

public class ct1701 {
    static int N;
    static int[] arr, dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N+1];

        st = new StringTokenizer(br.readLine());
        Arrays.fill(dp, Integer.MIN_VALUE);
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void go() {
        dp[0]=arr[0];
        int max = dp[0];
        for (int i=1;i<N;i++) {
            dp[i] = Math.max(arr[i], dp[i-1] + arr[i]);
            max = Math.max(max, dp[i]);
        }

        System.out.print(max);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}

