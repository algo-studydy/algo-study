import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class ct1104 {
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
    static int N, K, M;
    static int[][] map;
    static boolean[][] visited;
    static Deque<Point> dQ, startList;
    static ArrayList<Point> rockList;
    static int tmp, ans;
    static int[][] delta = {
            {1,0},
            {-1,0},
            {0,-1},
            {0,1}
    };

    static boolean isValid(int row, int col) {
        return (row>=0&&col>=0&&row<N&&col<N);
    }

    static void checkArea() {
        int count = startList.size();

        while (count-- > 0) {
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
                    if (map[nextRow][nextCol] == 1) continue;

                    visited[nextRow][nextCol] = true;
                    dQ.add(new Point(nextRow, nextCol));
                }
            }

            startList.add(sPoint);
        }
    }

    static void go(int idx, int deletedCount) {
        if (deletedCount == M) {
            // 도달가능한 칸의 수 계산
            visited = new boolean[N][N];
            tmp = 0;
            checkArea();
            ans = Math.max(ans, tmp);
            return;
        }

        for (int i=idx+1;i<rockList.size();i++) {
            Point rock = rockList.get(i);
            map[rock.row][rock.col] = 0;
            go(idx+1, deletedCount+1);
            map[rock.row][rock.col] = 1;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dQ = new ArrayDeque<>();
        startList = new ArrayDeque<>();
        rockList = new ArrayList<>();

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) rockList.add(new Point(r,c));
            }
        }

        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            startList.add(new Point(r,c));
        }

        go(-1, 0);

        System.out.print(ans);
    }
}
