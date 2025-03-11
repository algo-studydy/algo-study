import java.util.*;

public class BOJ23288주사위굴리기2 {
    static int n, m;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};  // 동, 남, 서, 북
    static int[] dc = {1, 0, -1, 0};
    static int r, c, dir = 0;  // 초기 좌표(0,0), 초기 방향(동쪽)
    static int[] dice = {3, 5, 4, 2, 1, 6};  // 동, 남, 서, 북, 앞, 뒤
    static int[][] dice_result = {
            // 각 방향으로 굴렸을 때, 동, 남, 서, 북, 앞, 뒤에 해당하는 index
            {4, 1, 5, 3, 2, 0},  // 동쪽으로 굴릴때의 변화
            {0, 4, 2, 5, 3 ,1},  // 남쪽으로 굴릴때의 변화
            {5, 1, 4, 3, 0, 2},  // 서쪽으로 굴릴때의 변화
            {0, 5, 2, 4, 1, 3}   // 북쪽으로 굴릴때의 변화
    };
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int k = sc.nextInt();  // 이동하는 횟수
        int answer = 0;

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i < k; i++){
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            // 1. 이동 방향에 칸이 없다면
            if(nr < 0 || nr >= n || nc < 0 || nc >= m) {
                dir = (dir + 2) % 4;  // 이동 방향을 반대로 변경
            }
            diceRoll();  // 주사위를 한번 굴린 후 전개도 갱신
            int cnt = bfs(map[r][c]);  // 연속해서 이동 가능한 칸의 수
            answer += cnt * map[r][c];  // 정답 갱신

            // 주사위 아래의 수가 칸에 적힌 수보다 크면 이동 방향을 시계 방향으로 90도 회전
            if(dice[5] > map[r][c]) dir = (dir + 1) % 4;
                // 주사위 아래의 수가 칸에 적힌 수보다 작으면 이동 방향을 반시계 방향으로 90도 회전
            else if(dice[5] < map[r][c]) dir = (dir + 3) % 4;
        }

        System.out.println(answer);
    }

    private static void diceRoll() {
        int[] temp = dice.clone();  // 현재 주사위의 전개도

        for(int i = 0; i < 6; i++){
            dice[i] = temp[dice_result[dir][i]];
        }

        // 주사위의 좌표 변경
        r = r + dr[dir];
        c = c + dc[dir];
    }

    private static int bfs(int score) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        visited[r][c] = true;
        q.offer(new int[] {r, c});
        int cnt = 1;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++){
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                if(nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == score && !visited[nr][nc]) {
                    q.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }


}
