import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] vDochi;
    static boolean[][] vWater;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};

    static class Point{
        int r;
        int c;
        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        Point dochi = null;
        map = new char[R][C]; // 지도
        vDochi = new boolean[R][C]; // 고슴도치 방문배열
        vWater = new boolean[R][C]; // 물 방문배열

        for(int i=0; i<R; i++){
            String s = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'S'){
                    dochi = new Point(i,j);
                }
            }
        } // 입력끝

        int answer = bfs(dochi);
        if(answer == -1){
            System.out.println("KAKTUS");
            return;
        }

        System.out.println(answer);
    }

    private static int bfs(Point dochi) {
        Queue<Point> qDochi = new LinkedList<>();
        Queue<Point> qWater = new LinkedList<>();

        qDochi.add(dochi);
        vDochi[dochi.r][dochi.c] = true;

        // 물위치 전부체크해서 큐에 넣기
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == '*') {
                    qWater.add(new Point(i,j));
                    vWater[i][j] = true;
                }
            }
        }

        int ans = 0; // 정답용 변수

        while(!qDochi.isEmpty()){
            ans++;
            int dochiSize = qDochi.size();
            int waterSize = qWater.size(); // 1분간 움직일 수 있는 범위 체크

            // 물 bfs
            // 물이 찰 예정인 곳은 고슴도치가 움직일 수 없으므로 물 먼저 시물레이션
            for(int t=0; t<waterSize; t++){
                Point p = qWater.poll();
                for(int d=0; d<4; d++){
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if(nr>=0 && nr<R && nc>=0 && nc<C){
                        if(!vWater[nr][nc] && !vDochi[nr][nc] && map[nr][nc] != 'D' && map[nr][nc] != 'X'){
                            qWater.add(new Point(nr, nc));
                            vWater[nr][nc] = true;
                        }
                    }
                }
            }

            // 고슴도치 bfs
            for(int t=0; t<dochiSize; t++){
                Point p = qDochi.poll();
                for(int d=0; d<4; d++){
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if(nr>=0 && nr<R && nc>=0 && nc<C){
                        if(!vWater[nr][nc] && !vDochi[nr][nc] && map[nr][nc] != 'X'){
                            if(map[nr][nc] == 'D') return ans; // 굴 만나면 종료

                            qDochi.add(new Point(nr, nc));
                            vDochi[nr][nc] = true;
                        }
                    }
                }
            }
        }

        return -1; //굴 못만나면 -1리턴
    }
}
