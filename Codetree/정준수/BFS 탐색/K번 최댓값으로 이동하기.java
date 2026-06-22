import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1103 {
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
    static int N, K;
    static int[][] map;
    static Deque<Point> dQ;
    static int[][] delta = {
            {1,0},
            {0,1},
            {0,-1},
            {-1,0}
    };
    static Point currentPoint;

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go() {
        while (K-- > 0) {
            boolean isUpdated = false;
            int currentValue = map[currentPoint.row][currentPoint.col];
            int maxValue = -1;

            dQ.add(new Point(currentPoint.row, currentPoint.col));
            boolean[][] visited = new boolean[N][N];
            visited[currentPoint.row][currentPoint.col] = true;
            while (!dQ.isEmpty()) {
                Point point = dQ.poll();

                for (int d=0;d<delta.length;d++) {
                    int nextRow = point.row + delta[d][0];
                    int nextCol = point.col + delta[d][1];

                    if (!isValid(nextRow, nextCol)) continue;
                    if (visited[nextRow][nextCol]) continue;
                    if (map[nextRow][nextCol] >= currentValue) continue;

                    visited[nextRow][nextCol] = true;
                    dQ.add(new Point(nextRow, nextCol));

                    if (map[nextRow][nextCol] < maxValue) continue;

                    if (map[nextRow][nextCol] > maxValue) {
                        maxValue = map[nextRow][nextCol];
                        currentPoint.row = nextRow;
                        currentPoint.col = nextCol;
                        isUpdated = true;
                    } else {
                        if (nextRow < currentPoint.row || (nextRow == currentPoint.row && nextCol < currentPoint.col)) {
                            currentPoint.row = nextRow;
                            currentPoint.col = nextCol;
                        }
                    }
                }
            }

            if (!isUpdated) break;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dQ = new ArrayDeque<>();

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken())-1;
        int c = Integer.parseInt(st.nextToken())-1;
        currentPoint = new Point(r, c);

        go();

        System.out.print((currentPoint.row+1) + " " + (currentPoint.col+1));
    }
}
