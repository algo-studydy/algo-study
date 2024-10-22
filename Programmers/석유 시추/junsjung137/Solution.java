import java.util.*;

class Solution {
    static int answer;
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static final int[][] vector = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] amount;
    static int size;
    static HashSet<Integer> hSet;
    
    static void initializeData(int[][] land) {
        map = land;
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        hSet = new HashSet<>();
        amount = new int[m];
        size = 0;
        answer = 0;
    }
    
    static void solve() {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (visited[r][c]) continue;
                if (map[r][c] == 0) continue;
                
                size = 0;
                bfs(r, c);
                
                if (hSet.isEmpty()) continue;
                
                for (int p : hSet) {
                    amount[p] += size;
                    
                    if (amount[p] > answer) answer = amount[p];
                }
                
                hSet = new HashSet<>();
            }
        }
    }
    
    static void bfs(int startRow, int startCol) {
        Deque<Point> dQ = new ArrayDeque<>();
        dQ.add(new Point(startRow, startCol));
        visited[startRow][startCol] = true;
        
        while (!dQ.isEmpty()) {
            Point point = dQ.poll();
            int row = point.x;
            int col = point.y;
            
            size++;
            hSet.add(col);
            
            for (int[] v : vector) {
                int nextRow = row + v[0];
                int nextCol = col + v[1];

                if (!isValidRange(nextRow, nextCol)) continue;
                if (visited[nextRow][nextCol]) continue;
                if (map[nextRow][nextCol] == 0) continue;
                
                visited[nextRow][nextCol] = true;
                dQ.add(new Point(nextRow, nextCol));
            }
        }
    }
    
    static boolean isValidRange(int row, int col) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }
        
    public int solution(int[][] land) {
        initializeData(land);
        solve();
        
        return answer;
    }
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public String toString() {
            return "x: " + x
                + " y: " + y
                ;
        }
    }
}