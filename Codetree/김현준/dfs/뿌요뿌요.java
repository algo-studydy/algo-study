
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static boolean[][] v;
    static int max = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Integer> list = new ArrayList<>();
    static int[][] map;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        v = new boolean[N][N];
        int cnt = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(!v[i][j]) {
                    v[i][j] = true;
                    int ch = DFS(i, j);
                    max = Math.max(max, ch); // 그냥 블럭의 최대 갯수
                    if(ch < 4) continue; // 4개 미만이므로 안터짐
                    cnt++;
                }
            }
        }

        System.out.println(cnt + " " + max);
    }

    private static int DFS(int r, int c) {
        int cnt = 1;
        for(int k=0;k<4;k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];

            if(canGo(r, c, nr, nc)) {
                v[nr][nc] = true;
                cnt += DFS(nr, nc);
            }
        }

        return cnt;
    }

    private static boolean canGo(int r, int c, int nextR, int nextC) {
        if(!inRange(nextR, nextC)) return false;

        if(v[nextR][nextC] || map[r][c] != map[nextR][nextC]) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
