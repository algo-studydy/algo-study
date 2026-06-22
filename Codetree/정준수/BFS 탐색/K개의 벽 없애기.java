import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1204 {
    static class Point {
        int row,col,time;

        Point(int row, int col, int time) {
            this.row=row;
            this.col=col;
            this.time=time;
        }

        @Override
        public String toString() {
            return String.format("row: "+this.row+", col: "+this.col+", time: "+this.time);
        }
    }
    static int N, K;
    static int[][] map;
    static boolean[][] visited;
    static int[][] delta = {
            {-1,0},
            {1,0},
            {0,-1},
            {0,1}
    };
    static Point startPoint, endPoint;
    static ArrayList<Point> wList;
    static int ans;

    static boolean isValid(int row, int col) {return (row>=0&&col>=0&&row<N&&col<N);}

    static void dfs() {
        Deque<Point> dQ = new ArrayDeque<>();

        dQ.add(new Point(startPoint.row, startPoint.col, 0));
        while(!dQ.isEmpty()) {
            Point curPoint = dQ.poll();

            for (int d=0;d<delta.length;d++) {
                int nextRow = curPoint.row + delta[d][0];
                int nextCol = curPoint.col + delta[d][1];
                int nextTime = curPoint.time + 1;

                if (!isValid(nextRow, nextCol)) continue;
                if (map[nextRow][nextCol] == 1) continue;
                if (visited[nextRow][nextCol]) continue;

                visited[nextRow][nextCol] = true;
                dQ.add(new Point(nextRow, nextCol, nextTime));

                if (nextRow==endPoint.row&&nextCol==endPoint.col) {
                    ans = Math.min(ans, nextTime);
                    dQ.clear();
                    break;
                }
            }
        }
    }

    static void go(int startIdx, int count) {
        if (count == K) {
            visited = new boolean[N][N];
            dfs();
            return;
        }

        for (int i=startIdx;i<wList.size();i++) {
            Point p = wList.get(i);
            map[p.row][p.col] = 0;
            go(i+1, count + 1);
            map[p.row][p.col] = 1;
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
        wList = new ArrayList<>();
        ans = Integer.MAX_VALUE;

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c]==1) wList.add(new Point(r,c,0));
            }
        }

        st = new StringTokenizer(br.readLine());
        int r1 = Integer.parseInt(st.nextToken())-1;
        int c1 = Integer.parseInt(st.nextToken())-1;
        st = new StringTokenizer(br.readLine());
        int r2 = Integer.parseInt(st.nextToken())-1;
        int c2 = Integer.parseInt(st.nextToken())-1;
        startPoint = new Point(r1,c1,0);
        endPoint = new Point(r2,c2,0);

        go(0, 0);

        System.out.print(ans==Integer.MAX_VALUE ? -1 : ans);
    }
}
