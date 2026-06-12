import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1002 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int ans;
    static int[][] delta = {
            {1, 0},
            {0, 1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<M);
    }

    static void go(int row, int col) {
        if (ans == 1) return;

        if (row == N-1 && col == M-1) {
            ans = 1;
            return;
        }

        for (int d=0;d<delta.length;d++) {
            int nextRow = row + delta[d][0];
            int nextCol = col + delta[d][1];

            if (!isValid(nextRow, nextCol)) continue;
            if (map[nextRow][nextCol]==0) continue;
            if (visited[nextRow][nextCol]) continue;

            visited[nextRow][nextCol] = true;
            go(nextRow, nextCol);
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
        visited = new boolean[N][M];
        ans = 0;

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<M;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][0] = true;
        go(0, 0);

        System.out.print(ans);
    }
}
