import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ct1604 {
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

        Arrays.fill(dp, -1);
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void go() {
        dp[0] = 0;
        for (int i=0;i<=M;i++) {
            for (int j=0;j<N;j++) {
                int coinValue = arr[j];
                int beforeIdx = i - coinValue;

                if (beforeIdx < 0) continue;
                if (dp[beforeIdx] == -1) continue;
                dp[i] = Math.max(dp[i], dp[beforeIdx] + 1);
            }
        }

        System.out.print(dp[M]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
