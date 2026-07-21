import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1803 {
    static int N;
    static int[][] arr, dp;
    static int ans;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][3];
        dp = new int[N+1][3];

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            arr[i][0] = l;
            arr[i][1] = m;
            arr[i][2] = r;
        }
    }

    static void go() {
        for (int i=0;i<3;i++) dp[1][i] = arr[1][i];

        for (int n=2;n<=N;n++) {
            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    if (i==j) continue;
                    dp[n][i] = Math.max(dp[n][i], dp[n-1][j] + arr[n][i]);
                }
            }
        }

        for (int i=0;i<3;i++) ans = Math.max(ans, dp[N][i]);
        System.out.print(ans);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
