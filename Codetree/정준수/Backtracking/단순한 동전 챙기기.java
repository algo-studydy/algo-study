import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0803 {
    static class Point {
        int row, col;

        Point(int row, int col) {
            this.row=row;
            this.col=col;
        }

        @Override
        public String toString() {
            return String.format("row: "+row+" col: "+col);
        }
    }
    static int N;
    static int[][] map;
    static int moveCount;
    static int ans;
    static Point[] list;
    static Point startPoint, endPoint;

    static int calcManhattenDistance(Point p1, Point p2) {
        return (Math.abs(p1.row - p2.row) + Math.abs(p1.col - p2.col));
    }

    static void go(int currIdx, int count) {
        for (int idx=currIdx+1;idx<10;idx++) {
            if (list[idx] == null) continue;

            int cost = calcManhattenDistance(list[currIdx], list[idx]);

            moveCount += cost;
            if (count + 1 >= 3) {
                ans = Math.min(ans, moveCount + calcManhattenDistance(list[idx], endPoint));
            }
            go(idx, count + 1);

            moveCount -= cost;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        list = new Point[10];
        ans = Integer.MAX_VALUE;

        for (int i=0;i<N;i++) {
            String inputString = br.readLine();
            for (int j=0;j<N;j++) {
                String input = inputString.charAt(j)+"";

                if ("S".equals(input)) {
                    list[0] = new Point(i, j);
                    startPoint = new Point(i, j);
                    continue;
                }
                if ("E".equals(input)) {
                    endPoint = new Point(i, j);
                    continue;
                }

                map[i][j] = ".".equals(input) ? 0 : Integer.parseInt(input);
                if (map[i][j] > 0) list[map[i][j]] = new Point(i, j);
            }
        }

        go(0, 0);

        System.out.print(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
