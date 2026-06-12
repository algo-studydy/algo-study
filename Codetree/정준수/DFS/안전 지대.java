import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1004 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int count;
    static int K;
    static int ans, areaCount;

    static int[][] delta = {
            {1, 0},
            {-1, 0},
            {0, -1},
            {0, 1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<M);
    }

    static void dfs(int row, int col) {
        for (int d=0;d<delta.length;d++) {
            int nextRow = row + delta[d][0];
            int nextCol = col + delta[d][1];

            if (!isValid(nextRow, nextCol)) continue;
            if (visited[nextRow][nextCol]) continue;
            if (map[nextRow][nextCol] <= K) continue;

            visited[nextRow][nextCol] = true;
            dfs(nextRow, nextCol);
        }
    }

    static void go() {
        while (K > 0) {
            visited = new boolean[N][M];
            count = 0;

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (visited[r][c]) continue;
                    if (map[r][c] <= K) continue;

                    count+=1;
                    dfs(r, c);
                }
            }

            if (areaCount <= count) {
                ans = K;
                areaCount = count;
            }

            K -= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<M;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                K = Math.max(K, map[r][c]);
            }
        }

        go();

        System.out.print(ans + " " + areaCount);
    }
}
