import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N = 12;
    static int M = 6;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static char[][] map = new char[N][M];
    static boolean[][] visited;

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i=0; i<N; i++){
            map[i] = sc.nextLine().toCharArray();
        }

        int ans=0;

        while (true){
            visited = new boolean[N][M];

            boolean flag = false; // 연쇄 확인용 플래그

            for(int i=N-1; i>=0; i--){
                for(int j=0; j<M; j++){
                    if(map[i][j] != '.' && !visited[i][j]){
                        if(bfs(new Point(i,j))){
                            flag = true;
                        }
                    }
                }
            }
            puyoMove();

            if(flag){
                ans++;
            }else{
                System.out.println(ans);
                return;
            }
        }
    }

    private static void puyoMove() {
        for(int i=N-1; i>=0; i--){
            for(int j=0; j<M; j++){
                if(map[i][j] != '.'){
                    int tmp=-1;
                    for(int k=i+1; k<N; k++){
                        if(map[k][j] == '.') {
                            tmp = k;
                        }else{
                            break;
                        }
                    }
                    if(tmp != -1){
                        map[tmp][j] = map[i][j];
                        map[i][j] = '.';
                    }
                }
            }
        }
    }

    private static boolean bfs(Point point) {
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> list = new ArrayList<>();

        q.add(point);
        list.add(point);

        char puyo = map[point.r][point.c];
        visited[point.r][point.c] = true;

        while (!q.isEmpty()){
            Point p = q.poll();

            for(int d=0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(!visited[nr][nc] && map[nr][nc] == puyo){
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                        list.add(new Point(nr, nc));
                    }
                }
            }
        }

        if(list.size() >= 4){
            for(Point p : list){
                map[p.r][p.c] = '.';
            }
            return true;
        }

        return false;
    }
}
