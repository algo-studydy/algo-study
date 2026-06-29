
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
    static int N;
    static boolean[][] v;
    static int startR, startC, endR, endC;
    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[][] map;
    static int[][] tmp;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        tmp = new int[N][N];
        v = new boolean[N][N];
        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken())-1;
        startC = Integer.parseInt(st.nextToken())-1;
        endR = Integer.parseInt(st.nextToken())-1;
        endC = Integer.parseInt(st.nextToken())-1;

        if(startR == endR && startC == endC) {
            System.out.println(0);
            return;
        }

        BFS();

        System.out.println(tmp[endR][endC] != 0 ? tmp[endR][endC] : -1);
    }

    private static void BFS() {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(startR, startC));
        v[startR][startC] = true;
        while (!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<8;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(p.r, p.c, nr, nc)) {
                    v[nr][nc] = true;
                    tmp[nr][nc] = tmp[p.r][p.c] + 1;
                    Q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static boolean canGo(int br, int bc, int r, int c) {
        if(!inRange(r, c) || v[r][c] ) return false;

        if(tmp[r][c] != 0 && (tmp[br][bc] + 1 > tmp[r][c])) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

}
