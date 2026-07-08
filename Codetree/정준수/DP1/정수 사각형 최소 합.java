import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1402 {
    static int N;
    static int[][] map;
    static long[][] dp;

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go() {
        for (int r=1;r<N;r++) {
            for (int c=N-1;c>=0;c--) {
                long upVal = Integer.MAX_VALUE;
                long rightVal = Integer.MAX_VALUE;

                if (isValid(r-1, c)) upVal = dp[r-1][c];
                if (isValid(r, c+1)) rightVal = dp[r][c+1];

                dp[r][c] = map[r][c] + Math.min(rightVal, upVal);
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

        dp[0][N-1] = map[0][N-1];
        for (int i=N-2;i>=0;i--) dp[0][i] = map[0][i] + dp[0][i+1];
        go();

        System.out.print(dp[N-1][0]);
    }
}
