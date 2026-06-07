import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0805 {
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
    static int N, M;
    static Point[] pList;
    static ArrayList<Point> list, list2;
    static double tmp, ans;

    static double calcManhattenDistance(Point p1, Point p2) {
        int xDiff = Math.abs(p1.row - p2.row);
        int yDiff = Math.abs(p1.col - p2.col);

        return xDiff * xDiff + yDiff * yDiff;
    }

    static void calcDistance(int idx, int count) {
        if (count == 2) {
            double dist = calcManhattenDistance(list2.get(0), list2.get(1));
            if (tmp < dist) {
                tmp = calcManhattenDistance(list2.get(0), list2.get(1));
            }
            return;
        }

        for (int i=idx+1;i<M;i++) {
            Point p = list.get(i);
            list2.add(p);
            calcDistance(i, count + 1);
            list2.remove(list2.size()-1);
        }
    }

    static void go(int curIdx) {
        if (list.size() == M) {
            tmp = -1;
            calcDistance(-1, 0);
            ans = Math.min(ans, tmp);
            return;
        }

        for (int i=curIdx+1;i<N;i++) {
            Point p = pList[i];
            list.add(p);
            go(i);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pList = new Point[N];
        tmp = Integer.MIN_VALUE;
        ans = Integer.MAX_VALUE;
        list = new ArrayList<>();
        list2 = new ArrayList<>();

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            pList[i] = new Point(row, col);
        }

        go(-1);

        System.out.print((int) ans);
    }
}
