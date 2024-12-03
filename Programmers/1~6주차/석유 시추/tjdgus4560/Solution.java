import java.util.*;
class Solution {
    // 세로(n), 가로(m)
    static int[][] oilmap;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static int n;
    static int m;
    static ArrayList<Integer> oil;
    public int solution(int[][] land) {

        n = land.length;
        m = land[0].length;
        oil = new ArrayList<>();
        oil.add(0);
        oilmap = new int[n][m];
        int oilCount = 1;
        int max = 0;
        int count = 0;
        for (int i = 0; i < m; i++) {

            count = 0;
            int tmp = 0;
            HashSet<Integer> hs = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if(land[j][i] == 1 && oilmap[j][i]==0) {
                    count+=bfs(j,i,land,oilCount);
                    hs.add(oilCount);
                    oilCount++;
                }else if(land[j][i] == 1 && oilmap[j][i]!=0) {
                    if(!hs.contains(oilmap[j][i])) {
                        count+=oil.get(oilmap[j][i]);
                        hs.add(oilmap[j][i]);
                    }
                }
            }
            if(count > max) {
                max = count;
            }
        }

        int answer = max;
        return answer;
    }
    public int bfs(int r, int c, int[][] land, int oilCount) {
        int count = 1;
        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(r,c));
        oilmap[r][c] = oilCount;
        while(!q.isEmpty()) {
            Point p = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if(nr>=0 && nr < n && nc>=0 && nc < m && land[nr][nc] == 1 && oilmap[nr][nc]==0) {
                    oilmap[nr][nc] = oilCount;
                    q.offer(new Point(nr,nc));
                    count++;
                }
            }
        }
        oil.add(count);
        return count;
    }

    public static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}