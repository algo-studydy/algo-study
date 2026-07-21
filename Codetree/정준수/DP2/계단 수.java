import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ct1802 {
    static final int DIV = 1_000_000_007;
    static int N;
    static int[][] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][9+1];
    }

    static void go() {
        for (int i=1;i<=9;i++) dp[1][i] = 1;

        for (int i=2;i<=N;i++) {
            for (int num=0;num<=9;num++) {

                if (num-1 >= 0) dp[i][num] = (dp[i][num] + dp[i-1][num-1])%DIV;
                if (num+1 <= 9) dp[i][num] = (dp[i][num] + dp[i-1][num+1])%DIV;
            }
        }

        int ans = 0;
        for (int i=0;i<=9;i++) ans = (ans + dp[N][i])%DIV;

        System.out.print(ans);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
