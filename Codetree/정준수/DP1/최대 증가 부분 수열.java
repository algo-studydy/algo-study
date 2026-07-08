import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1501 {
    static int N;
    static int[] map;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N];
        dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) map[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (map[i] >= map[j]) continue;

                dp[j] = Math.max(dp[j], dp[i] + 1);
            }
        }

        int ans = 0;

        for (int i = 0; i < N; i++) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.print(ans+1);
    }
}
