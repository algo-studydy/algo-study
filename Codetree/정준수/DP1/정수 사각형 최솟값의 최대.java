import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1403 {
    static int N;
    static int[][] map;
    static long[][] dp;

    static void go() {
        dp[0][0] = map[0][0];
        for (int c=1;c<N;c++) dp[0][c] = Math.min(map[0][c], dp[0][c-1]);

        for (int r=1;r<N;r++) dp[r][0] = Math.min(map[r][0], dp[r-1][0]);

        for (int r=1;r<N;r++) {
            for (int c=1;c<N;c++) {
                long upVal = dp[r-1][c];
                long leftVal = dp[r][c-1];

                dp[r][c] = Math.max(leftVal, upVal);
                dp[r][c] = Math.min(dp[r][c], map[r][c]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new long[N][N];

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        go();

        System.out.print(dp[N-1][N-1]);
    }
}
