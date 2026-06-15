import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, num;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
    static Queue<Point> Q = new ArrayDeque<>();
    static int N, K;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int startR, startC;
    static int[][] map;
    static int max, newR, newC;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken())-1;
        startC = Integer.parseInt(st.nextToken())-1;
        for(int k=0;k<K;k++) {
            Q.offer(new Point(startR, startC));
            max = 0; newR = N-1; newC = N-1;
            v = new boolean[N][N];
            BFS();
        }

        System.out.println(++startR + " " + ++startC);
    }

    private static void BFS() {
        List<Point> list = new ArrayList<>();
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];

                if(canGo(nr, nc, map[startR][startC])) {
                    v[nr][nc] = true;
                    list.add(new Point(nr, nc, map[nr][nc]));
                    Q.offer(new Point(nr, nc));
                }
            }
        }

        list.sort((a, b) ->  {
            if(a.num == b.num) {
                if(a.r == b.r) {
                    return a.c - b.c;
                }
                return a.r - b.r;
            }
            return b.num - a.num;
        });

        if(!list.isEmpty()) {
            startR = list.get(0).r;
            startC = list.get(0).c;

        }
    }

    private static boolean canGo(int r, int c, int s) {
        if(!inRange(r, c)) return false;

        if(map[r][c] >= s || v[r][c]) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
