import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
        }

        int ans1 =0;
        int ans2 =0;
        visited = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]) {
                    bfs1(i,j, map[i][j]);
                    ans1++;
                }
            }
        } //일반

        visited = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]) {
                    bfs2(i,j, map[i][j]);
                    ans2++;
                }
            }
        }

        System.out.println(ans1+" "+ans2);
    }
    static class Point{
        int r;
        int c;

        public Point(int r, int c){
            this.r = r;
            this.c = c;
        }

    }

    private static void bfs1(int i, int j, char c) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i,j));
        visited[i][j] = true;

        while(!q.isEmpty()){
            Point p = q.poll();
            for(int d=0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr>=0 && nr<N && nc>=0 && nc<N){
                    if(!visited[nr][nc] && map[nr][nc] == c){
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }

        }

    }

    private static void bfs2(int i, int j, char c) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(i,j));
        visited[i][j] = true;

        while(!q.isEmpty()){
            Point p = q.poll();
            for(int d=0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr>=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc]){
                    if(c == 'B'){
                        if(map[nr][nc] == 'B'){
                            q.add(new Point(nr, nc));
                            visited[nr][nc] = true;
                        }
                    }else{
                        if(map[nr][nc] != 'B'){
                            q.add(new Point(nr, nc));
                            visited[nr][nc] = true;
                        }
                    }
                }
            }

        }

    }
}
