import java.util.*;

class Solution {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] map;
    static int n, m;
    static Queue<int[]> q = new ArrayDeque<int[]>();
    static boolean[][] visited;
    static int[] oilSum;

    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = land[i][j];
            }
        }

        oilSum = new int[land[0].length];  // 한 열에서 채취할 수 있는 총 석유량을 저장하기 위한 배열
        visited = new boolean[n][m];  // 한번 방문했던 섬을 기록하기 위한 방문 배열

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(map[j][i] == 1 && !visited[j][i]){
                    q.add(new int[] {j, i});
                    bfs(j, i);
                }
            }
        }
        for(int o : oilSum) answer = Math.max(answer, o);
        return answer;
    }

    public static void bfs(int x, int y){
        visited[x][y] = true;
        int cnt = 1;
        Set<Integer> oil = new HashSet<>();  // 열 저장용 set

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            oil.add(cur[1]);
            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 1 && !visited[nr][nc]){
                    q.add(new int[] {nr, nc});
                    visited[nr][nc] = true;
                    cnt++;
                }
            }
        }
        // 하나의 열에서 추출될 수 있는 석유량의 합
        for(int o : oil) oilSum[o] += cnt;
    }
}