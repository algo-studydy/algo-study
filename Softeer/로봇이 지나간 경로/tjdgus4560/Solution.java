import java.io.*;
import java.util.*;

public class Main {
    static int[] dr = {-1,0,1,0}; // 상우하좌
    static int[] dc = {0,1,0,-1};
    public static class Point{
        int r;
        int c;
        int d;
        public Point(){}
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
        public Point(int r, int c, int d){
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        //입력
        char[][] map = new char[H][W];
        for(int i=0; i<H; i++){
            map[i] = br.readLine().toCharArray();
        }

        // 시작, 끝 지점 탐색
        Point[] startEnd = new Point[2];
        int tmp = 0;
        L : for(int r=0; r<map.length; r++){
            for(int c=0; c<map[r].length; c++){
                if(map[r][c] == '#'){
                    int count=0;
                    int direction = -1;
                    for(int i=0; i<4; i++){
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if(count > 1) break;
                        if(nr>=0 && nr<H && nc>=0 && nc<W && map[nr][nc] == '#'){
                            count++;
                            direction = i;
                        }
                    }
                    if(count == 1){
                        startEnd[tmp++] = new Point(r,c,direction);
                    }
                    if(tmp == 2){
                        break L;
                    }
                }
            }
        }
        Point p = new Point(startEnd[0].r, startEnd[0].c, startEnd[0].d);

        StringBuilder ans = new StringBuilder();

        while( !(p.r == startEnd[1].r && p.c == startEnd[1].c) ){
            int lr = p.r + dr[(p.d+3)%4];
            int lc = p.c + dc[(p.d+3)%4];
            int rr = p.r + dr[(p.d+1)%4];
            int rc = p.c + dc[(p.d+1)%4];
            if(lr>=0 && lr<H && lc>=0 && lc<W && map[lr][lc] == '#'){
                //왼쪽회전
                p.d = (p.d+3)%4;
                ans.append("L");
            }else if(rr>=0 && rr<H && rc>=0 && rc<W && map[rr][rc] == '#'){
                //오른쪽회전
                p.d = (p.d+1)%4;
                ans.append("R");
            }
            ans.append("A");
            p.r += dr[p.d]*2;
            p.c += dc[p.d]*2;
        }
        System.out.println((startEnd[0].r+1) + " " +(startEnd[0].c+1));
        switch(startEnd[0].d){
            case 0:
                System.out.println('^');
                break;
            case 1:
                System.out.println('>');
                break;
            case 2:
                System.out.println('v');
                break;
            case 3:
                System.out.println('<');
                break;
        }
        System.out.println(ans);
    }
}