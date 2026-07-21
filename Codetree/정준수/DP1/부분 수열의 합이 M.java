import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ct1602 {
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
        Arrays.sort(arr);
    }

    static void go() {
        for (int i=N-1;i>=0;i--) {
            int element = arr[i];
            dp[element] = 1;

            for (int j=M;j>element;j--) {
                if (element + j > M) continue;
                if (dp[j] == MAX) continue;

                dp[element + j] = Math.min(dp[element + j], dp[j] + 1);
            }
        }

        System.out.print(dp[M] == MAX ? -1 : dp[M]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
