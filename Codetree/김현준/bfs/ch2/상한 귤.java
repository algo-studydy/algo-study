
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
    static Queue<Point> Q = new ArrayDeque<>();
    static int N, K;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] tmp;
    static int[][] map;
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
                if(map[i][j] == 2){
                    Q.offer(new Point(i, j));
                }
            }
        }

        BFS();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(tmp[i][j] == Integer.MAX_VALUE) tmp[i][j] = -2;
                System.out.print(tmp[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void BFS() {
        tmp = new int[N][N];
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 0) tmp[i][j] = -1;
                else if(map[i][j] == 1) tmp[i][j] = Integer.MAX_VALUE;
            }
        }
        while(!Q.isEmpty()){
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(p.r, p.c, nr, nc)) {
                    tmp[nr][nc] = tmp[p.r][p.c] + 1;
                    Q.offer(new Point(nr, nc));
                }
            }
        }
    }

    private static boolean canGo(int br, int bc, int r, int c) {
        if(!inRange(r, c)) return false;

        // 현재 위치에서 +1 하는값이 기존 값보다 크면 X
        if(tmp[r][c] == -1 || tmp[br][bc] + 1 >= tmp[r][c]) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

