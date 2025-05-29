import java.util.*;

public class Main {
    static class Point{
        int r;
        int c;
        int dir; //방향
        int count; // 꺾은 횟수

        public Point(int r, int c, int dir, int count) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.count = count;
        }
    }

    static int N; // 열
    static int M; // 행
    static char[][] map;
    static int[][][] visited; // 거울 갯수로 관리되는 방문배열(거울갯수가 더 적은경우 재방문 하기 위함)
    static int[] dr = {0,-1,0,1};
    static int[] dc = {1,0,-1,0}; // 동 남 서 북 (시계방향)
    static List<Point> startEnd;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        map = new char[N][M];

        sc.nextLine(); //버퍼제거
        startEnd = new ArrayList<>();

        for(int i=0; i<N; i++){
            String s = sc.nextLine();
            for(int j=0; j<M; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'C') startEnd.add(new Point(i,j,0,0));
            }
        }

        bfs();
        int ans=Integer.MAX_VALUE;
        for(int i=0; i<4; i++){
            Point p = startEnd.get(1);
            ans = Math.min(ans, visited[p.r][p.c][i]);
        }

        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Point> q = new LinkedList<>();
        visited = new int[N][M][4];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }


        // 시작지점부터 4방향 q에 저장
        for(int d=0; d<4; d++){
            Point p = startEnd.get(0);
            q.add(new Point(p.r, p.c, d, 0));
            visited[p.r][p.c][d] = 0;
        }



        while(!q.isEmpty()){
            Point p = q.poll();

            int nr = p.r + dr[p.dir];
            int nc = p.c + dc[p.dir];

            // 전진
            if(nr>=0 && nr<N && nc>=0 && nc<M){
                if(map[nr][nc] != '*' && visited[nr][nc][p.dir] > p.count){
                    q.add(new Point(nr, nc, p.dir, p.count));
                    visited[nr][nc][p.dir] = p.count;
                }
            }

            //방향전환, 거울설치
            int rightDir = (p.dir+1)%4;
            int leftDir = (p.dir+3)%4;
            if(visited[p.r][p.c][leftDir] > p.count+1){
                q.add(new Point(p.r, p.c, leftDir, p.count+1));
                visited[p.r][p.c][leftDir] = p.count+1;
            }
            if(visited[p.r][p.c][rightDir] > p.count+1){
                q.add(new Point(p.r, p.c, rightDir, p.count+1));
                visited[p.r][p.c][rightDir] = p.count+1;
            }
        }

    }
}
