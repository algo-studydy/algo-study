import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1202 {
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
    static int N;
    static boolean[][] visited;
    static int[][] delta = {
            {-2,-1},
            {-2,1},
            {-1,-2},
            {-1,2},
            {2,-1},
            {2,1},
            {1,-2},
            {1,2}
    };
    static Point startPoint, targetPoint;
    static int ans;

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go() {
        Deque<Point> dQ = new ArrayDeque<>();

        dQ.add(new Point(startPoint.row, startPoint.col, 0));
        visited[startPoint.row][startPoint.col] = true;

        if (startPoint.row==targetPoint.row && startPoint.col==targetPoint.col) {
            ans = 0;
            return;
        }

        while (!dQ.isEmpty()) {
            Point curPoint = dQ.poll();

            for (int d=0;d<delta.length;d++) {
                int nextRow = curPoint.row + delta[d][0];
                int nextCol = curPoint.col + delta[d][1];
                int nextSize = curPoint.size + 1;

                if (!isValid(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol]) continue;

                visited[nextRow][nextCol] = true;
                dQ.add(new Point(nextRow, nextCol, nextSize));

                if (nextRow==targetPoint.row && nextCol==targetPoint.col) {
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

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken())-1;
        int c1 = Integer.parseInt(st.nextToken())-1;
        int r2 = Integer.parseInt(st.nextToken())-1;
        int c2 = Integer.parseInt(st.nextToken())-1;
        startPoint = new Point(r1, c1, 0);
        targetPoint = new Point(r2, c2, 0);

        ans = -1;
        go();

        System.out.print(ans);
    }
}
