
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dir;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int max = 0;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dir = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                dir[i][j] = Integer.parseInt(st.nextToken())-1;
            }
        }
        st = new StringTokenizer(br.readLine());
        int startR = Integer.parseInt(st.nextToken())-1;
        int startC = Integer.parseInt(st.nextToken())-1;
        choose(startR, startC, 0);
        System.out.println(max);
    }

    private static void choose(int startR, int startC, int cnt) {
        max = Math.max(max, cnt);
        // basis -> 이동이 불가하면 종료

        // inductive
        int direction = dir[startR][startC];
        int step = 1;
        while(true) {
            int nr = startR + dr[direction] * step;
            int nc = startC + dc[direction] * step;
            if(!inRange(nr, nc)) break;

            if(map[nr][nc] > map[startR][startC]) {
                choose(nr, nc, cnt + 1);
            }
            step++;
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

}
