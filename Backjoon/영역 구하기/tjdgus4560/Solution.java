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
    static int N;
    static int M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        M = sc.nextInt();
        N = sc.nextInt();
        int k = sc.nextInt();

        visited = new boolean[M][N];

        for(int i=0; i<k; i++){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            for(int c=x1; c<x2; c++){
                for(int r=y1; r<y2; r++){
                    visited[r][c] = true;
                }
            }
        }

        List<Integer> li = new ArrayList<>();
        int count=0;
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(!visited[i][j]){
                    count++;
                    li.add(bfs(i,j));
                }
            }
        }

        li.sort((a,b) -> {return a-b; });

        System.out.println(count);
        for(int n : li){
            System.out.print(n+" ");
        }
    }

    private static int bfs(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        visited[r][c] = true;

        int count = 1;
        while (!q.isEmpty()){
            Point p = q.poll();

            for(int d=0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr>=0 && nr<M && nc>=0 && nc<N){
                    if(!visited[nr][nc]){
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
