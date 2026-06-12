import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1105 {
    static class Point {
        int row, col;

        Point(int row, int col) {
            this.row=row;
            this.col=col;
        }

        @Override
        public String toString() {
            return String.format("row: "+row+", col: "+col);
        }
    }
    static int N, M;
    static int[][] map;
    static int time, glacierCount, deletedCount, lastCount;
    static int[][] delta = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<M);
    }

    static void go() {
        Deque<Point> waterQ = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        waterQ.add(new Point(0, 0));
        while(glacierCount>deletedCount) {
            time++;
            Deque<Point> waterCandidate = new ArrayDeque<>();

            while(!waterQ.isEmpty()) {
                Point water = waterQ.poll();

                for (int d=0;d<delta.length;d++) {
                    int nextRow = water.row + delta[d][0];
                    int nextCol = water.col + delta[d][1];

                    if (!isValid(nextRow, nextCol)) continue;
                    if (visited[nextRow][nextCol]) continue;

                    visited[nextRow][nextCol] = true;
                    if (map[nextRow][nextCol]==0) {
                        waterQ.add(new Point(nextRow, nextCol));
                    } else {
                        waterCandidate.add(new Point(nextRow, nextCol));
                    }
                }
            }

            lastCount = glacierCount - deletedCount;
            for (Point glacier : waterCandidate) {
                map[glacier.row][glacier.col] = 0;
                deletedCount++;
            }
            waterQ = waterCandidate;
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
                glacierCount += map[r][c];
            }
        }

        go();

        System.out.print(time+" "+lastCount);
    }
}

