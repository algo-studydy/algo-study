import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1203 {
    static class Point {
        int row, col, time;

        Point(int row, int col, int time) {
            this.row=row;
            this.col=col;
            this.time=time;
        }

        @Override
        public String toString() {
            return String.format("row: "+row+", col: "+col+", time: "+time);
        }
    }
    static int N, H, M;
    static int[][] map, ans;
    static boolean[][] visited;
    static int[][] delta = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    static Deque<Point> hQ;

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go() {
        while (!hQ.isEmpty()) {
            Point human = hQ.poll();

            Deque<Point> dQ = new ArrayDeque<>();
            dQ.add(new Point(human.row, human.col, 0));
            visited = new boolean[N][N];
            while (!dQ.isEmpty()) {
                Point curPoint = dQ.poll();

                for (int d = 0; d < delta.length; d++) {
                    int nextRow = curPoint.row + delta[d][0];
                    int nextCol = curPoint.col + delta[d][1];
                    int nextTime = curPoint.time + 1;

                    if (!isValid(nextRow, nextCol)) continue;
                    if (map[nextRow][nextCol] == 1) continue;
                    if (visited[nextRow][nextCol]) continue;

                    visited[nextRow][nextCol] = true;
                    dQ.add(new Point(nextRow, nextCol, nextTime));

                    if (map[nextRow][nextCol]==3) {
                        ans[human.row][human.col] = nextTime;
                        dQ.clear();
                        break;
                    }
                }
            }

            if (ans[human.row][human.col] == 0) ans[human.row][human.col] = -1;
        }
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                sb.append(ans[r][c]);
                if (c!=N-1) sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString().trim());
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        ans = new int[N][N];

        hQ = new ArrayDeque<>();
        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) hQ.add(new Point(r, c, 0));
            }
        }

        go();

        printMap();
    }
}
