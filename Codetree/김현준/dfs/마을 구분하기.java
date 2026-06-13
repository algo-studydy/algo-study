
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Integer> list = new ArrayList<>();
    static int[][] map;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 1 && !v[i][j]) {
                    v[i][j] = true;
                    int c = DFS(i, j);
                    list.add(c);
                }
            }
        }

        Collections.sort(list);

        System.out.println(list.size());
        for(int i : list) {
            System.out.println(i);
        }
    }

    private static int DFS(int r, int c) {

        int cnt = 1;
        for(int k=0;k<4;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if(canGo(nr, nc)) {
                v[nr][nc] = true;
                cnt += DFS(nr, nc);
            }
        }

        return cnt;
    }

    private static boolean canGo(int r, int c) {
        if(!inRange(r, c)) return false;

        if(map[r][c] == 0 || v[r][c]) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
