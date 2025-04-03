import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int[][] room = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean[][] cleaned = new boolean[N][M];
        int cnt = 0;
        int[] dRow = {-1,0,1,0};
        int[] dCol = {0,1,0,-1};
        while(true){
            if(!cleaned[r][c]){
                cleaned[r][c] = true;
                cnt++;
            }
            boolean found = false;
            for(int i = 0; i < 4; i++){
                d = (d + 3) % 4;
                int nr = r + dRow[d];
                int nc = c + dCol[d];
                if(nr >= 0 && nr < N && nc >= 0 && nc < M && room[nr][nc] == 0 && !cleaned[nr][nc]){
                    r = nr; c = nc;
                    found = true;
                    break;
                }
            }
            if(found) continue;
            int back = (d + 2) % 4;
            int brd = r + dRow[back];
            int bcd = c + dCol[back];
            if(brd >= 0 && brd < N && bcd >= 0 && bcd < M && room[brd][bcd] == 0){
                r = brd; c = bcd;
            } else {
                break;
            }
        }
        System.out.println(cnt);
    }
}
