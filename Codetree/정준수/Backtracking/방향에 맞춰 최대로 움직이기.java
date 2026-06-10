import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0703 {
    static class Point {
        int num, d;

        Point(int num, int d) {
            this.num=num;
            this.d=d;
        }

        @Override
        public String toString() {
            return String.format("num: "+num+" d: "+d);
        }
    }
    static int N;
    static Point[][] map;
    static boolean[][] visited;
    static int moveCount;
    static int ans;
    static int[][] delta = {
            {0,0},
            {-1,0},
            {-1,1},
            {0,1},
            {1,1},
            {1,0},
            {1,-1},
            {0,-1},
            {-1,-1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void go(int curRow, int curCol) {
        moveCount += 1;
        ans = Math.max(ans, moveCount);

        Point p = map[curRow][curCol];
        int w = N;
        while (w-- > 1) {
            int nextRow = curRow + w * delta[p.d][0];
            int nextCol = curCol + w * delta[p.d][1];

            if (!isValid(nextRow, nextCol)) continue;
            if (p.num > map[nextRow][nextCol].num) continue;

            go(nextRow, nextCol);
        }

        moveCount -= 1;
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new Point[N][N];
        visited = new boolean[N][N];

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                int num = Integer.parseInt(st.nextToken());
                map[r][c] = new Point(num, -1);
            }
        }
        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                int d = Integer.parseInt(st.nextToken());
                map[r][c].d = d;
            }
        }

        st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken()) - 1;
        int startCol = Integer.parseInt(st.nextToken()) - 1;

        go(startRow, startCol);

        System.out.print(ans-1);
    }
}
