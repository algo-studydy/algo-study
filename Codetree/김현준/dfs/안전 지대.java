import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] v;
    static int d, max;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Integer> list = new ArrayList<>();
    static int[][] map;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M]; d = 1; max = 0;
        int k = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                k = Math.max(k, map[i][j]);
            }
        }

        for(int i = 1; i <= k; i++) {
            v = new boolean[N][M];
            int cnt = 0;
            for(int r = 0; r < N; r++) {
                for(int c = 0; c < M; c++) {
                    if(map[r][c] > i && !v[r][c]) {
                        v[r][c] = true;
                        DFS(r, c, i);
                        cnt++;
                    }
                }
            }
            if(cnt > max) {
                max = cnt;
                d = i;
            }
        }

        System.out.println(d + " " + max);

    }

    private static void DFS(int r, int c, int h) {

        for(int k=0;k<4;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if(canGo(nr, nc, h)) {
                v[nr][nc] = true;
                DFS(nr, nc, h);
            }
        }
    }

    private static boolean canGo(int r, int c, int h) {
        if(!inRange(r, c)) return false;

        if(v[r][c] || map[r][c] <= h) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
