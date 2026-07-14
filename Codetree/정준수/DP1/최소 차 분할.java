import java.io.*;
import java.util.*;

public class ct1609 {
    static final int MAX = 100_000;
    static int N, sum;
    static int[] arr, dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[MAX+1];

        st = new StringTokenizer(br.readLine());
        Arrays.fill(dp, MAX);
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
    }

    static void go() {
        dp[0] = 0;
        int diff = MAX;

        for (int i=0;i<N;i++) {
            int element = arr[i];

            for (int j=MAX;j>=0;j--) {
                int num = j - element;

                if (num < 0) continue;
                if (dp[num] == MAX) continue;

                dp[j] = Math.min(dp[j], 1);
            }
        }

        for (int i=0;i<=MAX;i++) {
            if (dp[i]==MAX) continue;

            if (i != sum) diff = Math.min(diff, Math.abs(i - Math.abs(sum-i)));
        }
        System.out.print(diff);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
