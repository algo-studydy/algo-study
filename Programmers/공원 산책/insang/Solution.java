import java.util.*;
class Solution {
    public int[] solution(String[] park, String[] routes) {
        int n = park.length;
        int m = park[0].length();
        int sR = 0;
        int sC = 0;

        String[][] map = new String[n][m];
        for(int i = 0; i < n; i++){
            String[] split = park[i].split("");
            for(int j = 0; j < m; j++){
                if(split[j].equals("S")){
                    sR = i;
                    sC = j;
                    map[i][j] = "O";
                }
                else map[i][j] = split[j];
            }
        }
        String[] direction = {"E", "S", "W", "N"};
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        for(String route : routes){
            String[] split = route.split(" ");
            int dir = Arrays.asList(direction).indexOf(split[0]);
            int dist = Integer.parseInt(split[1]);
            int nr = sR;
            int nc = sC;
            boolean flag = false;
            for(int i = 0; i < dist; i++){
                nr += dr[dir];
                nc += dc[dir];
                if(nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc].equals("X")){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                sR = nr;
                sC = nc;
            }
        }

        return new int[] {sR, sC};
    }
}