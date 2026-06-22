
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, H, M;
    static List<Point> list = new ArrayList<>();
    static boolean[][] v;
    static int startR, startC, endR, endC;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int[][] tmp;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        tmp = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 3) list.add(new Point(i, j));
            }
        }

        BFS();
        int[][] ans = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 2) {
                    ans[i][j] = tmp[i][j] != 0 ? tmp[i][j] : -1;
                }
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void BFS() {
        Queue<Point> Q = new ArrayDeque<>();
        v = new boolean[N][N];
        for(Point p : list) {
            Q.offer(p);
            v[p.r][p.c] = true;
        }

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(nr, nc)) {
                    if(tmp[nr][nc] == 0) tmp[nr][nc] = tmp[p.r][p.c] + 1;
                    else if(tmp[p.r][p.c] + 1 < tmp[nr][nc]) {
                        tmp[nr][nc] = tmp[p.r][p.c] + 1;
                    }
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static boolean canGo(int r, int c) {
        if(!inRange(r, c) || v[r][c]) return false;

        if(map[r][c] == 1) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
