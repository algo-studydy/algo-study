import java.util.*;

public class Main {
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static int N,M;
    static boolean[][][] visited;
    static int[][] map;
    static Point start, end;

    public static class Point{
        int r;
        int c;
        int time;
        boolean canBreak;
        public Point(int r, int c, int time, boolean canBreak){
            this.r = r;
            this.c = c;
            this.time = time;
            this.canBreak = canBreak;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M][2];

        start = new Point(sc.nextInt()-1, sc.nextInt()-1, 0, true);
        end = new Point(sc.nextInt()-1, sc.nextInt()-1, Integer.MAX_VALUE, true);

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        bfs();

        System.out.println(end.time == Integer.MAX_VALUE ? -1 : end.time);
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        visited[start.r][start.c][0] = true;

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int d=0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(nr == end.r && nc == end.c){
                        end.time = p.time+1;
                        return;
                    }
                    // 벽 아닐때
                    if(map[nr][nc] == 0){
                        // 벽을 부순적이 있고 부순후 방문배열 미방문일때
                        if(!p.canBreak && !visited[nr][nc][1]){
                            q.add(new Point(nr, nc, p.time+1, false));
                            visited[nr][nc][1] = true;
                        }
                        // 벽을 부술수 있고 부수지 않은 방문배열 미방문일때
                        if(p.canBreak && !visited[nr][nc][0]) {
                            q.add(new Point(nr, nc, p.time + 1, true));
                            visited[nr][nc][0] = true;
                        }
                        // 벽 만났을때
                    }else{
                        // 부수고 이동
                        if(p.canBreak && !visited[nr][nc][0]){
                            q.add(new Point(nr, nc, p.time+1, false));
                            visited[nr][nc][0] = true;
                        }
                    }
                }
            }
        }
    }
}
