import java.util.*;
import java.lang.*;
//동시에 두 수레를 같은 칸으로 움직일 수 없습니다.
//수레끼리 자리를 바꾸며 움직일 수 없습니다.
class Solution {
    static int min = Integer.MAX_VALUE;
    static int[] dr  = {0,1,0,-1};
    static int[] dc  = {1,0,-1,0};
    static Point red, blue, endR, endB;
    static boolean[][] checkR;
    static boolean[][] checkB;
    static int N,M;

    public int solution(int[][] maze) {
        int answer = 0;
        N = maze.length;
        M = maze[0].length;
        checkR = new boolean[N][M];
        checkB = new boolean[N][M];
        for(int i = 0; i < N ; i++ ){
            for(int j = 0 ; j < M ; j++ ){
                switch (maze[i][j]){
                    case 1 :
                        red = new Point(i,j);
                        break;
                    case 2 :
                        blue = new Point(i,j);
                        break;
                    case 3 :
                        endR = new Point(i,j);
                        break;
                    case 4 :
                        endB = new Point(i,j);
                        break;
                }
            }
        }
        // 초기 방문 체크
        checkR[red.r][red.c] = true;
        checkB[blue.r][blue.c] = true;

        dfs(red.r, red.c, blue.r, blue.c, maze, 0);

        return min != Integer.MAX_VALUE ? min : 0;

    }

    public void dfs(int RR, int RC, int BR, int BC, int[][] maze, int step){
        //종료조건
        if(RR == endR.r && RC == endR.c && BR == endB.r && BC == endB.c){
            min = Math.min(min, step);
            return;
        }
        //동시에 두 수레를 같은 칸으로 움직일 수 없습니다.
        if(RR == BR && RC == BC){
            return;
        }

        // 둘다 자리를 못찾았을경우
        if( !(RR == endR.r && RC == endR.c) && !(BR == endB.r && BC == endB.c)){
            //레드방향
            for(int i = 0; i < 4 ; i++){
                //블루방향
                for(int j = 0 ; j < 4 ; j++){
                    int nRR = RR + dr[i];
                    int nBR = BR + dr[j];
                    int nRC = RC + dc[i];
                    int nBC = BC + dc[j];
                    //레드 이동 가능 체크
                    if(nRR >= 0 && nRR < N && nRC >= 0 && nRC < M && !checkR[nRR][nRC] && maze[nRR][nRC] != 5) {
                        //블루 이동 가능 체크
                        if(nBR >= 0 && nBR < N && nBC >= 0 && nBC < M && !checkB[nBR][nBC] && maze[nBR][nBC] != 5) {

                            //수레끼리 자리를 바꾸며 움직일 수 없습니다.
                            if(RR == nBR && RC == nBC && BR == nRR && BC == nRC){
                                continue;
                            }

                            // 이동
                            checkR[nRR][nRC] = true;
                            checkB[nBR][nBC] = true;
                            dfs(nRR, nRC, nBR, nBC, maze, step+1);
                            checkR[nRR][nRC] = false;
                            checkB[nBR][nBC] = false;
                        }
                    }
                }
            }
            // 레드 도착 블루 미도착
        }else if( (RR == endR.r && RC == endR.c) && !(BR == endB.r && BC == endB.c) ){
            //블루방향
            for(int i = 0; i < 4 ; i++){
                int nBR = BR + dr[i];
                int nBC = BC + dc[i];
                //블루 이동 가능 체크
                if(nBR >= 0 && nBR < N && nBC >= 0 && nBC < M && !checkB[nBR][nBC] && maze[nBR][nBC] != 5) {
                    checkB[nBR][nBC] = true;
                    dfs(RR, RC, nBR, nBC, maze, step+1);
                    checkB[nBR][nBC] = false;
                }
            }
            // 블루 도착 레드 미도착
        }else if( !(RR == endR.r && RC == endR.c) && (BR == endB.r && BC == endB.c) ){
            //블루방향
            for(int i = 0 ; i < 4 ; i++){
                int nRR = RR + dr[i];
                int nRC = RC + dc[i];

                //레드 이동 가능 체크
                if(nRR >= 0 && nRR < N && nRC >= 0 && nRC < M && !checkR[nRR][nRC] && maze[nRR][nRC] != 5) {
                    // 이동
                    checkR[nRR][nRC] = true;
                    dfs(nRR, nRC, BR, BC, maze, step+1);
                    checkR[nRR][nRC] = false;
                }
            }
        }
    }

    public class Point{
        int r;
        int c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}