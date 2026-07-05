import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1404 {
    static int N;
    static int[][] map;
    static long[][] dp;
    static boolean[][] visited;
    static int[][] delta = {
            {0,-1},
            {0,1},
            {-1,0},
            {1,0}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static long dfs(int row, int col) {
        for (int d=0;d<delta.length;d++) {
            int nextRow = row + delta[d][0];
            int nextCol = col + delta[d][1];

            if (!isValid(nextRow, nextCol)) continue;
            if (map[nextRow][nextCol] <= map[row][col]) continue;

            if (dp[nextRow][nextCol] != 0) {
                dp[row][col] = Math.max(dp[row][col], dp[nextRow][nextCol] + 1);
                continue;
            }

            dp[row][col] = Math.max(dp[row][col], dfs(nextRow, nextCol) + 1);
        }

        return dp[row][col] == 0 ? 1 : dp[row][col];
    }

    static void go() {
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                if (dp[r][c] != 0) continue;

                dfs(r, c);
            }
        }
    }

    static long calcMax() {
        long maxVal = 0;
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                maxVal = Math.max(maxVal, dp[r][c]);
            }
        }

        return maxVal;
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

        System.out.print(calcMax());
    }
}
