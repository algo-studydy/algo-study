import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ct1605 {
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

        st = new StringTokenizer(br.readLine());
        Arrays.fill(dp, MAX);
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void go() {
        dp[0] = 0;
        for (int i=0;i<N;i++) {
            int element = arr[i];

            for (int j=M;j>=0;j--) {
                int num = j - element;

                if (num < 0) continue;
                if (dp[num] == MAX) continue;

                dp[j] = Math.min(dp[j], dp[num] + 1);
            }
        }

        System.out.print(dp[M] == MAX ? "No" : "Yes");
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
