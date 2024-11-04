import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static int answer;
    static int n;
    static int m;
    static boolean[][] visited;
    public static int solution(int[][] map, int[][] visitPoints){
        answer = 0;
        visited = new boolean[n][n];
        visited[visitPoints[0][0]][visitPoints[0][1]] = true;
        dfs(visitPoints[0][0], visitPoints[0][1], map , visitPoints,1);
        return answer;
    }

    private static void dfs(int r, int c, int[][] map, int[][] visitPoints, int idx) {
        //순서대로 방문지점 정상 도달시
        if(idx == m){
            answer++;
            return;
        }
        // 방문지점 잘못 도달시 종료
        if(!visitPointsCheck(r,c,visitPoints,idx)){
            return;
        }

        //그외 전진
        for(int i = 0; i < 4 ; i++){
            int nr = r+dr[i];
            int nc = c+dc[i];
            if(nr >= 0 && nr < n && nc >= 0 && nc < n && map[nr][nc] != 1 && !visited[nr][nc]){
                visited[nr][nc] = true;
                if(nr == visitPoints[idx][0] && nc == visitPoints[idx][1]){
                    idx++;
                    dfs(nr, nc, map, visitPoints, idx);
                    idx--;
                }else{
                    dfs(nr, nc, map, visitPoints, idx);
                }
                visited[nr][nc] = false;
            }
        }
    }

    private static boolean visitPointsCheck(int r, int c, int[][] visitPoints, int idx) {
        for(int i = 1; i < m ; i++){
            if(i == idx-1) continue;
            if(r == visitPoints[i][0] && c == visitPoints[i][1]){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] map = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j] = sc.nextInt();
            }
        }
        int[][] visitPoints = new int[m][2];
        for(int i=0;i<m;i++){
            visitPoints[i][0] = sc.nextInt() - 1;
            visitPoints[i][1] = sc.nextInt() - 1;
        }
        int answer = solution(map,visitPoints);
        System.out.println(answer);
    }
}