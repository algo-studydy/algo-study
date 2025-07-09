import java.util.*;

public class Main {
    static class Point {
        int r, c, t;
        Point(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t; // 이동 시간
        }
    }

    static int w, h;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Point> fireQ;
    static Queue<Point> manQ;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            w = sc.nextInt();
            h = sc.nextInt();

            map = new char[h][w];
            visited = new boolean[h][w];
            fireQ = new LinkedList<>();
            manQ = new LinkedList<>();

            for (int i=0; i<h; i++) {
                String s = sc.next();
                for (int j=0; j<w; j++) {
                    map[i][j] = s.charAt(j);

                    if (map[i][j] == '*') {
                        fireQ.add(new Point(i, j, 0));
                    }

                    if (map[i][j] == '@') {
                        manQ.add(new Point(i, j, 0));
                        visited[i][j] = true;
                    }
                }
            }

            int ans = bfs();
            if (ans == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(ans);
            }
        }
    }

    public static int bfs() {
        while (!manQ.isEmpty()) {
            // 불 확산
            int fireSize = fireQ.size();
            for (int i = 0; i < fireSize; i++) {
                Point f = fireQ.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = f.r + dr[d];
                    int nc = f.c + dc[d];

                    if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                        if (map[nr][nc] == '.' || map[nr][nc] == '@') {
                            map[nr][nc] = '*';
                            fireQ.add(new Point(nr, nc, 0));
                        }
                    }
                }
            }

            // 사람 이동
            int playerSize = manQ.size();
            for (int i = 0; i < playerSize; i++) {
                Point p = manQ.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];

                    // 맵 밖으로 나가면 탈출
                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) {
                        return p.t + 1;
                    }

                    if (map[nr][nc] == '.' && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        manQ.add(new Point(nr, nc, p.t + 1));
                    }
                }
            }
        }

        return -1;
    }
}
