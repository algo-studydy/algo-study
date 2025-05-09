import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BOJ10026적록색약 {
    static int n, rgb, rb;
    static char[][] map;
    static boolean[][] rgbVisited, rbVisited ;
    static Queue<int[]> q = new ArrayDeque<int[]>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String[] str = sc.next().split("");
            for (int j = 0; j < n; j++) {
                map[i][j] = str[j].charAt(0);
            }
        }

        rgbVisited = new boolean[n][n];
        rbVisited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 적록색약 아닌 경우
                if(!rgbVisited[i][j]) {
                    q.add(new int[] {i, j});
                    rgbVisited[i][j] = true;
                    bfs();
                }
                // 적록색약인 경우
                if(!rbVisited[i][j]) {
                    q.add(new int[] {i, j});
                    rbVisited[i][j] = true;
                    bfs2();
                }
            }
        }
        System.out.println(rgb + " " + rb);
    }
    private static void bfs2() {
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr >= 0 && nr < n && nc >= 0 && nc < n && !rbVisited[nr][nc]) {
                    // 현재 칸이 R 또는 G일 경우
                    if(map[r][c] == 'R' || map[r][c] == 'G') {
                        // 이웃 칸도 R 또는 G이면 같은 구역
                        if(map[nr][nc] == 'R' || map[nr][nc] == 'G') {
                            q.add(new int[] {nr, nc});
                            rbVisited[nr][nc] = true;
                        }
                    }else {
                        // B인 경우, 같은 색이어야 같은 구역
                        if(map[nr][nc] == map[r][c]) {
                            q.add(new int[] {nr, nc});
                            rbVisited[nr][nc] = true;
                        }
                    }
                }
            }
        }
        rb++;
    }

    private static void bfs() {
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr >= 0 && nr < n && nc >= 0 && nc < n && !rgbVisited[nr][nc] && map[r][c] == map[nr][nc]) {
                    q.add(new int[] {nr, nc});
                    rgbVisited[nr][nc] = true;
                }
            }
        }
        rgb++;
    }

}
