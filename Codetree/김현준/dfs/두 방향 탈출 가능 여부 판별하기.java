
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] v;
    static int[] dr ={0, 1};
    static int[] dc = {1, 0};
    static boolean flag = false;
    static int[][] map;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[N][M];
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);
        System.out.println(flag ? 1 : 0);
    }

    private static void DFS(int r, int c) {
        // basis
        if(r == N-1 && c == M-1) {
            flag = true;
            return;
        }

        // inductive
        for(int k=0;k<2;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if(canGo(nr, nc)) {
                v[nr][nc] = true;
                DFS(nr, nc);
            }
        }
    }

    private static boolean canGo(int r, int c) {
        // 밖으로 벗어나는지
        if(!inRange(r, c)) return false;
        // 가는길이 뱀으로 막히지 않았는지, 이미 간곳인지
        if(map[r][c] == 0 || v[r][c] ) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
