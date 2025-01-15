import java.util.Scanner;
public class Main {
    static int m, n;
    static int[][] map, dp;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        map = new int[m][n];
        dp = new int[m][n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
                dp[i][j] = -1;  // 방문한 적이 없는 칸을 -1로 초기화
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int r, int c) {
        // 끝 지점 도착 시 이동 경로 1증가
        if(r == m-1 && c == n-1) return 1;

        // 이전에 방문한 적이 있다면 재방문 X
        if(dp[r][c] != -1) return dp[r][c];

        dp[r][c] = 0; // 방문 체크
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nr < m && nc >= 0 && nc < n && map[nr][nc] < map[r][c]){
                dp[r][c] += dfs(nr, nc);
            }
        }
        return dp[r][c];
    }
}
