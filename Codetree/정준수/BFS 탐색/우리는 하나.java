import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1106 {
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
    static int N, K, U, D;
    static int[][] map;
    static boolean[][] visited;
    static Deque<Point> dQ, startList;
    static int tmp, ans;
    static int[][] delta = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static boolean canGo(int r1, int c1, int r2, int c2) {
        int diffHeight = Math.abs(map[r1][c1]-map[r2][c2]);
        return (diffHeight >= U && diffHeight <= D);
    }

    static void checkArea() {
        int size = startList.size();

        while (size-- > 0) {
            Point sPoint = startList.poll();

            if (visited[sPoint.row][sPoint.col]) continue;

            dQ.add(new Point(sPoint.row, sPoint.col));
            visited[sPoint.row][sPoint.col] = true;

            while (!dQ.isEmpty()) {
                Point point = dQ.poll();
                tmp += 1;

                for (int d=0;d<delta.length;d++) {
                    int nextRow = point.row + delta[d][0];
                    int nextCol = point.col + delta[d][1];

                    if (!isValid(nextRow, nextCol)) continue;
                    if (visited[nextRow][nextCol]) continue;
                    if (!canGo(point.row, point.col, nextRow, nextCol)) continue;

                    visited[nextRow][nextCol] = true;
                    dQ.add(new Point(nextRow, nextCol));
                }
            }
            startList.add(sPoint);
        }
    }

    static void go(int idx, int selectedCount) {
        if (selectedCount == K) {
            tmp = 0;
            visited = new boolean[N][N];
            checkArea();
            ans = Math.max(ans ,tmp);
            return;
        }

        for (int i=idx+1;i<N*N;i++) {
            int row = i / N;
            int col = i % N;
            startList.add(new Point(row, col));
            go(i+1, selectedCount+1);
            startList.pollLast();
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        startList = new ArrayDeque<>();
        dQ = new ArrayDeque<>();

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        go(-1, 0);

        System.out.print(ans);
    }
}
