
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
    static List<Point> list = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int[][] tmp;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        tmp = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[N][M];
        BFS();
        System.out.println(tmp[N - 1][M - 1] != 0 ? tmp[N - 1][M - 1] : -1);
    }

    private static void BFS() {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(0, 0));
        v[0][0] = true;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(nr, nc)) {
                    v[nr][nc] = true;
                    tmp[nr][nc] = tmp[p.r][p.c] + 1;
                    Q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static boolean canGo(int r, int c) {
        if(!inRange(r, c) || v[r][c]) return false;

        if(map[r][c] == 0 || tmp[r][c] != 0) return false;

        return true;
    }

}
