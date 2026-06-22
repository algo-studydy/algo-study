import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1201 {
    static class Point {
        int row, col, size;

        Point(int row, int col, int size) {
            this.row=row;
            this.col=col;
            this.size=size;
        }

        @Override
        public String toString() {
            return (String.format("row: "+this.row+", col: "+this.col+", size: "+this.size));
        }
    }
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[][] delta = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    static int ans;

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<M);
    }

    static void go() {
        Deque<Point> dQ = new ArrayDeque<>();

        dQ.add(new Point(0, 0, 0));
        while (!dQ.isEmpty()) {
            Point curPoint = dQ.poll();

            for (int d=0;d<delta.length;d++) {
                int nextRow = curPoint.row + delta[d][0];
                int nextCol = curPoint.col + delta[d][1];
                int nextSize = curPoint.size + 1;

                if (!isValid(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol] == 0) continue;
                if (visited[nextRow][nextCol]) continue;

                visited[nextRow][nextCol] = true;
                dQ.add(new Point(nextRow, nextCol, nextSize));

                if (nextRow==N-1 && nextCol==M-1) {
                    ans = nextSize;
                    dQ.clear();
                    break;
                }
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

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<M;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        ans = -1;
        go();

        System.out.print(ans);
    }
}
