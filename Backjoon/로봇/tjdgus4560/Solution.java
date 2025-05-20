import java.util.*;

public class Main {
    static class Point {
        int r, c, dir, cnt;

        Point(int r, int c, int dir, int cnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.cnt = cnt;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {0, 0, 0, 1, -1}; // 1 동, 2 서, 3 남, 4 북
    static int[] dc = {0, 1, -1, 0, 0};
    static Point start;
    static Point end;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] =sc.nextInt();
            }
        }

        start = new Point(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), 0);
        end = new Point(sc.nextInt()-1, sc.nextInt()-1, sc.nextInt(), 0);
        visited = new boolean[N][M][5];

        int ans = bfs();
        System.out.println(ans);
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        visited[start.r][start.c][start.dir] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();

            // 도착
            if(p.r == end.r && p.c == end.c && p.dir == end.dir) {
                return p.cnt;
            }

            // Go k 전진은 3칸까지 한번의 명령으로 가능
            for(int k=1; k<=3; k++) {
                int nr = p.r + dr[p.dir] * k;
                int nc = p.c + dc[p.dir] * k;

                if(nr>=0 && nr<N && nc>=0 && nc <M ){
                    if(map[nr][nc] == 1) break; //벽만나면 바로 중단

                    if(!visited[nr][nc][p.dir]) {
                        visited[nr][nc][p.dir] = true;
                        q.add(new Point(nr, nc, p.dir, p.cnt + 1));
                    }
                }

            }

            // Turn dir 90도 회전은 한번명령, 180도 회전은 두번명령
            for(int d=1; d<=4; d++) {
                if(d == p.dir) continue;

                int cost = turn(p.dir, d);

                if(!visited[p.r][p.c][d]) {
                    visited[p.r][p.c][d] = true;
                    q.add(new Point(p.r, p.c, d, p.cnt + cost));
                }
            }
        }

        return -1;
    }
    private static int turn(int from, int to) {
        // 1 동, 2 서, 3 남, 4 북
        if((from == 1 && to == 2) || (from == 2 && to == 1) || (from == 3 && to == 4) || (from == 4 && to == 3)) return 2; // 두번회전
        return 1; // 한번회전
    }

}
