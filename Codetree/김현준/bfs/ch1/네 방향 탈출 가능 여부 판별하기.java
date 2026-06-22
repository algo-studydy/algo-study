
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
    static int N, M;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        v = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v[0][0] = true;
        System.out.println(BFS(0, 0));
    }

    private static int BFS(int r, int c) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(r, c));
        boolean flag = false;

        while(!Q.isEmpty()) {
            Point p = Q.poll();

            if(p.r == N-1 && p.c == M-1) {
                flag = true; break;
            }

            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(nr, nc)) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                }
            }
        }

        return flag ? 1 : 0;
    }

    private static boolean canGo(int r, int c) {
        if(!inRange(r, c)) return false;

        if(map[r][c] == 0 || v[r][c]) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
