
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int N, R, C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.print(map[R][C] + " ");

        while(true) {
            if(!solve()) break;
            System.out.print(map[R][C] + " ");
        }


    }

    private static boolean solve() {
        int max = map[R][C];
        for(int k=0;k<4;k++) {
            int nr = R + dr[k];
            int nc = C + dc[k];
            if(!inRange(nr, nc)) continue;

            if(map[nr][nc] > max) {
                max = map[nr][nc];
                R = nr;
                C = nc;
                return true;
            }
        }

        return false;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

/**
 * 4 2
 * 1
 * 2
 * 2
 * 1
 *
 * -> 0
 */