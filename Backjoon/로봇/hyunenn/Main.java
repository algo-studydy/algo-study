import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int startR, startC, startDir;
    static int endR, endC, endDir;
    static boolean[][][] v;
    static int[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
        startDir = setDir(Integer.parseInt(st.nextToken()));

        st = new StringTokenizer(br.readLine());
        endR = Integer.parseInt(st.nextToken()) - 1;
        endC = Integer.parseInt(st.nextToken()) - 1;
        endDir = setDir(Integer.parseInt(st.nextToken()));

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(startR, startC, startDir, 0));
        v[startR][startC][startDir] = true;
        while (!Q.isEmpty()) {
            Point p = Q.poll();
            // 뽑은 값이 위치의 방향까지 정확하면 종료
            if(p.r == endR && p.c == endC && p.dir == endDir)
                return p.cnt;

            // 사방 탐색이 아니라, 현재 바라보고 있는 방향으로만 나아가야함
            int nr = p.r;
            int nc = p.c;
            int dist = 1;
            while(dist <= 3) {
                nr += dr[p.dir];
                nc += dc[p.dir];
                // 범위 안에 있고, 벽이 없어야 하며, 방문한 적이 없는 곳
                if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1) break;
                if(map[nr][nc] == 1) break;
                if(!v[nr][nc][p.dir]) {
                    v[nr][nc][p.dir] = true;
                    Q.offer(new Point(nr, nc, p.dir, p.cnt + 1));
                }
                dist++;
            }
            // 제자리에서 방향만 회전 - 시계, 반시계
            int right = (p.dir + 1) % 4;
            int left = (p.dir == 0) ? 3 : p.dir - 1;
            if(!v[p.r][p.c][right]) {
                v[p.r][p.c][right] = true;
                Q.offer(new Point(p.r, p.c, right, p.cnt + 1));
            }
            if(!v[p.r][p.c][left]) {
                v[p.r][p.c][left] = true;
                Q.offer(new Point(p.r, p.c, left, p.cnt + 1));
            }
        }
        return -1;
    }

    public static int setDir(int d) {
        switch (d) {
            case 1: return 1;
            case 2: return 3;
            case 3: return 2;
            case 4: return 0;
            default: return -1;
        }
    }
}
