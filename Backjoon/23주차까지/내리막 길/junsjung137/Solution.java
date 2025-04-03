import java.util.*;
import java.io.*;

public class Main {
    static int M, N;
    static int[][] map, dp;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dp[M-1][N-1] = 1;
        System.out.println(dfs(0, 0));
    }

    static final int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int dfs(int r, int c) {
        dp[r][c] = 0;
        for (int[] delta : vector) {
            int nextRow = r + delta[0];
            int nextCol = c + delta[1];

            if (!isValid(nextRow, nextCol)) {
                continue;
            }

            if (map[nextRow][nextCol] >= map[r][c]) {
                continue;
            }

            if (dp[nextRow][nextCol] != -1) {
                dp[r][c] += dp[nextRow][nextCol];
                continue;
            }
            dp[r][c] += dfs(nextRow, nextCol);
        }
        return dp[r][c];
    }

    static boolean isValid(int row, int col) {
        return row >= 0 && row < M && col >= 0 && col < N;
    }
}
