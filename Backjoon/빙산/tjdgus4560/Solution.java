import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        int year = 0;
        while(true){
            visited = new boolean[N][M];
            int count = 0; //나눠지는 갯수 세기용

            //빙산 덩어리 카운팅
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j]>0 && !visited[i][j]){
                        bfs(i, j);
                        count++;
                    }
                }
            }

            // 덩어리 2개이상 종료
            if(count>=2){
                System.out.println(year);
                return;
            }

            // 다녹음 종료
            if(count==0){
                System.out.println(0);
                return;
            }

            // 빙산 녹이기
            melt();
            year++;
        }
    }

    static void bfs(int r, int c){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        visited[r][c] = true;

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int d=0; d<4; d++){
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(map[nr][nc]>0 && !visited[nr][nc]){
                        visited[nr][nc] = true;
                        q.add(new Point(nr, nc));
                    }
                }
            }
        }
    }

    static void melt(){
        int[][] tmp = new int[N][M]; // 임시 저장 배열

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j]==0) continue;

                int count = 0;
                for(int d=0; d<4; d++){
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if(nr>=0 && nr<N && nc>=0 && nc<M){
                        if(map[nr][nc]==0) count++;
                    }
                }

                tmp[i][j] = Math.max(0, map[i][j] - count);
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = tmp[i][j];
            }
        }
    }
}
