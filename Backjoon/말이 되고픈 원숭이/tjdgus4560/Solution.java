import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] hr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] hc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static class Point {
        int r;
        int c;
        int time;
        int horse;

        public Point(int r, int c, int time, int horse) {
            this.r = r;
            this.c = c;
            this.time = time;
            this.horse = horse;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        K = sc.nextInt(); // 말처럼 K번 이동가능
        W = sc.nextInt(); // 가로
        H = sc.nextInt(); // 세로

        map = new int[H][W];
        visited = new boolean[H][W][K + 1]; // 0~K번 말 이동 가능 여부

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0, K));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.r == H - 1 && p.c == W - 1) {
                return p.time;
            }

            // 원숭이 이동
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
                    if (map[nr][nc] == 0 && !visited[nr][nc][p.horse]) {
                        q.add(new Point(nr, nc, p.time + 1, p.horse));
                        visited[nr][nc][p.horse] = true;
                    }
                }
            }

            // 말 이동
            if (p.horse > 0) {
                for (int d = 0; d < 8; d++) {
                    int nr = p.r + hr[d];
                    int nc = p.c + hc[d];

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W) {
                        if (map[nr][nc] == 0 && !visited[nr][nc][p.horse - 1]) {
                            q.add(new Point(nr, nc, p.time + 1, p.horse - 1));
                            visited[nr][nc][p.horse - 1] = true;
                        }
                    }
                }
            }
        }

        return -1;
    }
}

