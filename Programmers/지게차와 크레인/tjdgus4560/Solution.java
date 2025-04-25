import java.util.*;

class Point{
    int r;
    int c;

    public Point(int r, int c){
        this.r = r;
        this.c = c;
    }
}

class Solution {
    static int N;
    static int M;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    String [][] map;
    boolean [][] visited;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;

        N = storage.length;
        M = storage[0].length();
        map = new String[N+2][M+2]; //외곽 판별을 위해 +2씩 더함

        // 빈공간 초기화
        for(int i=0; i<N+2; i++){
            Arrays.fill(map[i], "-1");
        }

        // map 에 컨테이너 초기화
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i+1][j+1] = String.valueOf(storage[i].charAt(j));
            }
        }

        for(String request : requests){
            bfs(); //외곽 체크
            if(request.length() > 1){ //크레인 이용 해당 전체 컨테이너 삭제
                for(int i=1; i<=N; i++){
                    for(int j=1; j<=M; j++){
                        if(map[i][j].equals(String.valueOf(request.charAt(0)))) map[i][j] = "-1";
                    }
                }
            }else{ //지게차 이용
                for (int i=1; i<=N; i++) {
                    for (int j=1; j<=M; j++) {
                        if (map[i][j].equals(request)) {
                            if (check(i,j)) map[i][j] = "-1";
                        }
                    }
                }
            }
        }

        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(!map[i][j].equals("-1")) answer++;
            }
        }
        return answer;
    }

    // 외곽 확인 함수
    public boolean check(int r, int c) {
        for (int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (visited[nr][nc]) return true;
        }
        return false;
    }

    public void bfs(){
        visited = new boolean[N+2][M+2];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0,0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Point p = q.poll();
            for(int d=0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if(nr >=0 && nr<N+2 && nc>=0 && nc<M+2){
                    if(!visited[nr][nc] && map[nr][nc].equals("-1")){
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }

}