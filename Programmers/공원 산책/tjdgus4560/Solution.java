import java.util.*;

class Solution {
    char[][] map;
    int[] dr = {0,0,1,-1};
    int[] dc = {1,-1,0,0}; // 동서남북
    int R,C;

    public class Point{
        int r;
        int c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }


    public int[] solution(String[] park, String[] routes) {
        R = park.length;
        C = park[0].length();

        map = new char[R][];

        Point cur=null;
        for(int i=0; i<R; i++){
            map[i] = park[i].toCharArray();
            for(int j=0; j<C; j++){
                if(map[i][j] == 'S') cur = new Point(i,j);
            }
        }



        L:for(int i=0; i<routes.length; i++){
            String[] route = routes[i].split(" ");
            int dir = toDir(route[0].charAt(0));
            int dist = Integer.parseInt(route[1]);

            int nr = cur.r;
            int nc = cur.c;
            for(int j=0; j<dist; j++){
                nr += dr[dir];
                nc += dc[dir];
                if(nr<0 || nr>=R || nc<0 || nc>=C) continue L;
                if(map[nr][nc] == 'X') continue L;
            }
            cur.r = nr;
            cur.c = nc;
        }

        int[] answer = {cur.r, cur.c};
        return answer;
    }

    public int toDir(char c){
        if(c == 'E') return 0;
        if(c == 'W') return 1;
        if(c == 'S') return 2;
        return 3;
    }
}