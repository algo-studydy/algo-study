import java.util.*;

class Solution {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] map;
    static int startR, startC, maxR, maxC;
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        maxR = park.length;
        maxC = park[0].length();
        map = new char[park.length][park[0].length()];
        for(int i=0;i<park.length;i++) {
            String s = park[i];
            for(int j=0;j<park[i].length();j++) {
                map[i][j] = park[i].charAt(j);
                if(map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                }
            }
        }

        for(int i=0;i<routes.length;i++) {
            String[] s = routes[i].split(" ");
            int dir = 0;
            switch(s[0]) {
                case "N":
                    dir = 0;
                    break;
                case "E":
                    dir = 1;
                    break;
                case "S":
                    dir = 2;
                    break;
                case "W":
                    dir = 3;
                    break;
            }
            int cnt = 0;
            int nr = startR;
            int nc = startC;
            boolean flag = true;
            while(cnt < Integer.parseInt(s[1])) {
                nr += dr[dir];
                nc += dc[dir];
                // 나가거나 걸리면 정지
                if(!inRange(nr, nc) || map[nr][nc] == 'X') {
                    flag = false;
                    break;
                }

                cnt++;
            }

            if(flag) {
                startR = nr;
                startC = nc;
            }

            // System.out.println(startR + " " + startC);
        }
        answer[0] = startR; answer[1] = startC;
        return answer;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < maxR && c >= 0 && c < maxC;
    }
}