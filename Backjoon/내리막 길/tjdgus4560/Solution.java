import java.util.Scanner;

public class Main {
    static int N, M;
    static int[][] map;
    // 현재 지점부터 마지막지점까지 도달 가능한 경로의 수
    static int[][] dp;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));
    }


    public static int dfs(int r, int c) {
        // 목표 지점
        if (r == N - 1 && c == M - 1) {
            return 1;
        }

        // 이미 계산된 경로의 수가 있는 경우
        if (dp[r][c] != -1) {
            return dp[r][c];
        }

        // 일단 0으로 초기화
        dp[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int nr = dr[i] + r;
            int nc = dc[i] + c;

            if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] < map[r][c]) {
                dp[r][c] += dfs(nr, nc);
            }
        }

        return dp[r][c];
    }

}
