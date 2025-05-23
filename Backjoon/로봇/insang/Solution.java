import java.util.*;

public class BOJ1726로봇 {
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int startR = sc.nextInt() - 1;
        int startC = sc.nextInt() - 1;
        int startD = sc.nextInt() - 1;

        int endR = sc.nextInt() - 1;
        int endC = sc.nextInt() - 1;
        int endD = sc.nextInt() - 1;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][4];
        q.offer(new int[] {startR, startC, startD, 0});
        visited[startR][startC][startD] = true;

        int answer = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            int cur[] = q.poll();
            int r = cur[0], c = cur[1], d = cur[2], depth = cur[3];

            // 도착지 도달 시 정답 갱신 후 반복 종료
            if(r == endR && c == endC && d == endD){
                answer = depth;
                break;
            }

            // 현재 로봇이 바라보는 방향으로 1~3칸 전진
            for(int i = 1; i <= 3; i++){
                int nr = r + dr[d] * i;
                int nc = c + dc[d] * i;
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc][d]) continue;
                // 1~3칸 전진 중 한번이라도 벽을 만나면 break
                if(map[nr][nc] == 1) break;

                q.offer(new int[] {nr, nc, d, depth+1});
                visited[nr][nc][d] = true;
            }

            // 현재 로봇의 위치에서 회전
            for(int i = 0; i < 4; i++){
                int cnt = 0;
                if(i != d && !visited[r][c][i]){
                    if(check(i, d)) cnt = 2;
                    else cnt = 1;
                    q.offer(new int[] {r, c, i, depth + cnt});
                    visited[r][c][i] = true;
                }
            }
        }

        System.out.println(answer);
    }

    // 로봇이 2번 회전해야 하는지 판단하는 함수
    private static boolean check(int d, int i) {
        return ((d == 0 && i == 1) || (d == 1 && i == 0)
                || (d == 2 && i == 3) || (d == 3 && i == 2));
    }
}
