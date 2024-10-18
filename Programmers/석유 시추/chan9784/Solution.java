import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int index = 2;
    
    public int solution(int[][] land) {
        int answer = 0;
        ArrayList<Integer> area = new ArrayList<>();
        
        visited = new boolean[land.length][land[0].length];
        
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land[0].length; j++) {
                if(!visited[i][j] && land[i][j] == 1) {
                    visited[i][j] = true;
                    area.add(dfs(land, i, j, 1));
                    index++;
                }
            }
        }
        for(int i = 0; i < land[0].length; i++) {
            int temp = 0;
            ArrayList<Integer> confirm = new ArrayList<>();
            for(int j = 0; j < land.length; j++) {
                if(land[j][i] != 0 && !confirm.contains(land[j][i])) {
                    temp += area.get(land[j][i] - 2);
                    confirm.add(land[j][i]);
                }
            }
            answer = Math.max(answer, temp);
        }
        return answer;
    }
    
    static int dfs(int[][] land, int x, int y, int cnt) {
        land[x][y] = index;
            
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx >= land.length || nx < 0 || ny >= land[0].length || ny < 0 || visited[nx][ny] || land[nx][ny] == 0){
                continue;
            }
            visited[nx][ny] = true;
            cnt = dfs(land, nx, ny, cnt + 1);
        }
        return cnt;
    }
}