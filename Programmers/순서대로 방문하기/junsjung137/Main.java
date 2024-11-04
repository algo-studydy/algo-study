import java.io.*;
import java.util.*;

public class Main {
    static int answer;
    static int n, m;
    static int[][] map;
    static List<Point> list;
    static boolean[][] memo;
    
    public static void main(String[] args) throws IOException{
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        memo = new boolean[n][n];
        list = new ArrayList<>();
        
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = 1;
            list.add(new Point(y, x));
        }
/*
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(map[r][c] + " ");
            } System.out.println();
        } System.out.println();
        for (int i = 0; i < m; i++) {
            System.out.print(list.get(i) + " ");
        } System.out.println(); 
*/
        Point startPoint = list.get(0);
        map[startPoint.y][startPoint.x] = 1;
        memo[startPoint.y][startPoint.x] = true;
        dfs(startPoint.y, startPoint.x, startPoint, 0);
        System.out.print(answer);
    }

    static int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static void dfs(int row, int col, Point dest, int idx) {
        if (row == dest.y && col == dest.x) {
            if (idx + 1 == list.size()) {
                // System.out.println("목적지 도착");
                answer += 1;
                return;
            }
            // System.out.println("목적 IDX: " + (idx + 1));
            Point nextPoint = list.get(idx + 1);
            map[nextPoint.y][nextPoint.x] = 0;
            map[dest.y][dest.x] = 1;
            dfs(row, col, nextPoint, idx + 1);
            map[nextPoint.y][nextPoint.x] = 1;
            map[dest.y][dest.x] = 0;
            return;
        }

        for (int[] v : vector) {
            int nextRow = row + v[0];
            int nextCol = col + v[1];

            if (!isValidRange(nextRow, nextCol)) continue;
            if (map[nextRow][nextCol] == 1) continue;
            if (memo[nextRow][nextCol]) continue;
            memo[nextRow][nextCol] = true;
            // System.out.print(nextRow + " " + nextCol + " > ");
            dfs(nextRow, nextCol, dest, idx);
            memo[nextRow][nextCol] = false;
        }
    }

    static boolean isValidRange(int row, int col) {
        return row >= 0 && col >= 0 && row < n && col < n;
    }

    static class Point {
        int x, y;

        Point(int y, int x) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + y + ", " +
                x + ")";
        }
    }
}
