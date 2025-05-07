import java.util.*;

public class Main {
    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N,M;
    static Point start, end;
    static boolean[][][] visited; // [r][c][t(0~3)]
    static char[][] map;

    static int[] dr = {0,1,0,-1}; //0 : 오른쪽, 1 : 아래, 2 : 왼쪽, 3 : 위
    static int[] dc = {1,0,-1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        start = new Point(sc.nextInt()-1, sc.nextInt()-1);
        end = new Point(sc.nextInt()-1, sc.nextInt()-1);
        map = new char[N][M];
        visited = new boolean[N][M][4];

        sc.nextLine(); // 개행문자 제거

        for(int i=0; i<N; i++){
            String s = sc.nextLine();
            for(int j=0; j<M; j++){
                map[i][j] = s.charAt(j);
            }
        } // 입력끝

        // 각타임마다 유령이 바라보는곳 전처리
        for(int t=0; t<4; t++){
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] != '.' && map[i][j] != '#'){
                        int d = (map[i][j] - '0' + t) % 4; //시각에 따른 바라보는 방향
                        ghostSee(i, j, t, d);
                    }
                }
            }
        }

        int ans = bfs();
        System.out.println(ans == -1 ? "GG" : ans);
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(start);
        int time =0;
        visited[start.r][start.c][time] = true;

        while(!q.isEmpty()){
            time++;

            int size = q.size();
            for(int s=0; s<size; s++){
                Point p = q.poll();

                if(!visited[p.r][p.c][time%4]){
                    q.add(p); //제자리에서 움직이지 않는경우
                    visited[p.r][p.c][time%4] = true;
                }

                for(int d=0; d<4; d++){
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];

                    if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc][time%4] && map[nr][nc] == '.'){

                        if(nr == end.r && nc == end.c) return time; //종료지점 도달 time리턴

                        q.add(new Point(nr, nc));
                        visited[nr][nc][time%4] = true;
                    }
                }
            }


        }

        return -1;
    }

    // 유령이 시각에 따라 바라보는곳 전처리용
    private static void ghostSee(int i, int j, int t, int d) {
        visited[i][j][t] = true;
        int nr = i + dr[d];
        int nc = j + dc[d];

        while(nr>=0 && nr<N && nc>=0 && nc<M && map[nr][nc] == '.'){
            visited[nr][nc][t] = true;
            nr+=dr[d];
            nc+=dc[d];
        }
    }
}
