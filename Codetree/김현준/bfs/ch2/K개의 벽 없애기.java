
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, cnt;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static int N, K;
    static List<Point> walls = new ArrayList<>();
    static List<Point> list = new ArrayList<>();
    static boolean[][] v;
    static int startR, startC, endR, endC;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int[][] tmp;
    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) walls.add(new Point(i, j));
            }
        }

        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken())-1; startC = Integer.parseInt(st.nextToken())-1;
        st = new StringTokenizer(br.readLine());
        endR = Integer.parseInt(st.nextToken())-1; endC = Integer.parseInt(st.nextToken())-1;

        DFS(0);
        System.out.println(min != Integer.MAX_VALUE ? min : -1);
    }

    private static void DFS(int idx) {
        // basis
        if(idx == K) {
            // 지도를 복사해서, 벽을 지운다.
            copyMap();
            for(Point p : list) {
                tmp[p.r][p.c] = 0;
            }
            min = Math.min(min, BFS());
            return;
        }
        // inductive
        for(Point p : walls) {
            list.add(p);
            DFS(idx + 1);
            list.remove(list.size() - 1);
        }
    }

    private static int BFS() {
        int ans = Integer.MAX_VALUE;
        Queue<Point> Q = new ArrayDeque<>();
        v = new boolean[N][N];
        v[startR][startC] = true;
        Q.offer(new Point(startR, startC, 0));

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            if(p.r == endR && p.c == endC) {
                ans = Math.min(ans, p.cnt);
                continue;
            }
            for(int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(p.r, p.c, nr, nc)) {
                    v[nr][nc] = true;
                    tmp[nr][nc] = tmp[p.r][p.c] + 1;
                    Q.offer(new Point(nr, nc, p.cnt + 1));
                }
            }
        }

        return ans;
    }

    private static boolean canGo(int br, int bc, int r, int c) {
        // 범위, 방문배열 처리
        if(!inRange(r, c) || v[r][c]) return false;

        //
        if(tmp[r][c] == 1) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    private static void copyMap() {
        tmp = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tmp[i][j] = map[i][j];
            }
        }
    }
}
