import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1101 {
    static class Point {
        int row, col;

        Point (int row, int col) {
            this.row=row;
            this.col=col;
        }

        @Override
        public String toString() {
            return (String.format("row: "+this.row+", col: "+this.col));
        }
    }
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Deque<Point> dQ;
    static int ans;
    static int[][] delta = {
            {1,0},
            {-1,0},
            {0,-1},
            {0,1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<M);
    }

    static void go() {
        dQ.add(new Point(0, 0));
        visited[0][0] = true;

        while (!dQ.isEmpty()) {
            Point point = dQ.poll();

            if (point.row == N-1 && point.col == M-1) {
                ans = 1;
                break;
            }

            for (int d=0;d<delta.length;d++) {
                int nextRow = point.row + delta[d][0];
                int nextCol = point.col + delta[d][1];

                if (!isValid(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] == 0) continue;

                visited[nextRow][nextCol] = true;
                dQ.add(new Point(nextRow, nextCol));
            }
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
        dQ = new ArrayDeque<>();

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<M;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        go();

        System.out.print(ans);
    }
}
