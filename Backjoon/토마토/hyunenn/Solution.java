import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, h, cnt;
        Point(int r, int c, int h, int cnt) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.cnt = cnt;
        }
    }
    static int N, M, H;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[] dh = {-1, 1};
    static int[][][] map;
    static Queue<Point> Q = new ArrayDeque<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N][M][H];
        for(int h=0;h<H;h++) {
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<M;j++) {
                    map[i][j][h] = Integer.parseInt(st.nextToken());
                    if(map[i][j][h] == 1)
                        Q.offer(new Point(i, j, h, 0));
                }
            }
        }

        System.out.println(tomato());
    }

    private static int tomato() {
        int result = -1;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            result = p.cnt;
            // 4방 탐색
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(!inRange(nr, nc, p.h)) continue;
                if(map[nr][nc][p.h] == 0) {
                    map[nr][nc][p.h] = 1;
                    Q.offer(new Point(nr, nc, p.h, p.cnt + 1));
                }
            }
            // 상하 탐색
            for(int k=0;k<2;k++) {
                int nh = p.h + dh[k];
                if(!inRange(p.r, p.c, nh)) continue;
                if(map[p.r][p.c][nh] == 0) {
                    map[p.r][p.c][nh] = 1;
                    Q.offer(new Point(p.r, p.c, nh, p.cnt + 1));
                }
            }
        }

        if(checkMap()) return result;
        else return -1;
    }

    private static boolean checkMap() {
        for(int h=0;h<H;h++) {
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(map[i][j][h] == 0)
                        return false;
                }
            }
        }

        return true;
    }

    private static boolean inRange(int r, int c, int h) {
        return r >= 0 && r < N && c >= 0 && c < M && h >= 0 && h < H;
    }
}