import java.util.*;

class Solution {
    int[] dr = {0,1,0,-1};
    int[] dc = {1,0,-1,0};
    boolean[][] check;
    public int[] solution(String[][] places) {
        int[] answer = {1,1,1,1,1};
        for(int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                for(int k = 0; k < 5; k++) {
                    if(places[i][j].charAt(k) == 'P'){
                        if(bfs(i,j,k,places) == false){
                            answer[i] = 0;
                        }
                    }
                }
            }

        }

        return answer;
    }

    private boolean bfs(int n, int r, int c,String[][] places) {
        check = new boolean[5][5];
        Queue<Point> q = new LinkedList<>();

        q.offer(new Point(r, c));
        check[r][c] = true;
        int step = 0;
        while (!q.isEmpty()) {
            if(step==2){
                break;
            }
            int size = q.size();
            for (int s = 0; s < size; s++) {


                int cr = q.peek().r;
                int cc = q.peek().c;

                q.poll();
                for(int i=0;i<4;i++){
                    int nr = cr+dr[i];
                    int nc = cc+dc[i];
                    if(nr>=0 && nr<5 && nc>=0 && nc<5 && places[n][nr].charAt(nc) != 'X' && !check[nr][nc]){
                        check[nr][nc] = true;
                        if(places[n][nr].charAt(nc) == 'P'){
                            return false;
                        }
                        q.offer(new Point(nr,nc));
                    }
                }
            }
            step++;
        }
        return true;
    }

    public class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }
}