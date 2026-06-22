import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1205 {
    static class Point {
        int row, col, time;

        Point(int row, int col, int time) {
            this.row=row;
            this.col=col;
            this.time=time;
        }

        @Override
        public String toString() {return String.format("row: "+this.row+", col: "+this.col+", time: "+this.time);}
    }
    static int N, K;
    static int[][] map;
    static int[][] delta = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    static int[][] ans;
    static Deque<Point> dQ;

    static boolean isValid(int row, int col) {return (row>=0&&col>=0&&row<N&&col<N);}

    static void go() {
        while (!dQ.isEmpty()) {
            Point curPoint = dQ.poll();
            int curRow = curPoint.row;
            int curCol = curPoint.col;
            int time = curPoint.time;

            ans[curRow][curCol] = time;
            for (int d=0;d<delta.length;d++) {
                int nextRow = curRow + delta[d][0];
                int nextCol = curCol + delta[d][1];

                if (!isValid(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol]!=1) continue;

                map[nextRow][nextCol] = 2;
                dQ.add(new Point(nextRow,nextCol,time+1));
            }
        }
    }

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int r=0;r<N;r++) {
            for (int c=0;c<N;c++) {
                sb.append(ans[r][c]);
                if(c!=N-1) sb.append(" ");
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
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        ans = new int[N][N];
        dQ = new ArrayDeque<>();

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c]==2) dQ.add(new Point(r,c,0));
                else if (map[r][c]==1) ans[r][c] = -2;
                else ans[r][c] = -1;
            }
        }

        go();

        printMap();
    }
}
