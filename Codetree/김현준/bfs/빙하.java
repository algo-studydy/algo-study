
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
    }
    static int N, M;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = 0;
        int time = 0, ans = 0;
        while(true) {
            int ch = BFS();
            if(ch == 0) {
                time = t;
                break;
            }
            ans = ch;
            t++;
        }

        System.out.println(time + " " + ans);

    }

    private static int BFS() {
        int[][] tmp = new int[N][M];
        v = new boolean[N][M];
        Queue<Point> Q = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        int cnt = 0;
        Q.offer(new Point(0, 0));
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(!inRange(nr, nc) || v[nr][nc]) continue;

                if(map[p.r][p.c] == 0) {
                    if(map[nr][nc] == 1) {
                        tmp[nr][nc] = 0;
                        cnt++;
                    }
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                }
            }
        }

        map = tmp;

        return cnt;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }


}
