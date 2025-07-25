import java.util.*;

public class BOJ14923미로탈출 {
    static int n, m, eR, eC, answer;
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static Queue<int[]> q = new ArrayDeque<>();
    static boolean[][][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int sR = sc.nextInt() - 1;
        int sC = sc.nextInt() - 1;
        eR = sc.nextInt() - 1;
        eC = sc.nextInt() - 1;
        answer = Integer.MAX_VALUE;

        visited = new boolean[n][m][2];
        map = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }
        visited[sR][sC][0] = true;
        q.offer(new int[] {sR, sC, 0, 0});  // [r, c, 벽부순여부, depth]
        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void bfs() {
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], wall = cur[2], cnt = cur[3];

            if(r == eR && c == eC){
                answer = cnt;
                return;
            }

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc][wall]){
                    // 벽을 부수지 않고 전진
                    if(map[nr][nc] == 0) {
                        q.offer(new int[] {nr, nc, wall, cnt+1});
                        visited[nr][nc][wall] = true;
                    }

                    // 벽 부수고 전진
                    else {
                        // 이미 벽을 부순적 있으면 continue
                        if(wall == 1) continue;
                        q.offer(new int[] {nr, nc, 1, cnt+1});
                        visited[nr][nc][1] = true;
                    }
                }
            }
        }
    }
}
