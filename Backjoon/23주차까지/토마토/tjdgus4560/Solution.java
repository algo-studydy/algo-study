import java.io.*;
import java.util.*;

public class Main {

    static int M; // 가로 칸수
    static int N; // 세로 칸수
    static int H; // 높이

    static int[] dr = {0, 1, 0, -1, 0, 0};
    static int[] dc = {1, 0, -1, 0, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};

    static int[][][] arr;
    static boolean[][][] visited;

    static class Point{
        int r;
        int c;
        int h;

        public Point(int h, int r, int c) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][N][M];
        visited = new boolean[H][N][M];
        //  1 익은 토마토,
        //  0 익지 않은 토마토,
        //  -1 토마토가 들어있지 않은 칸

        int tmtCount =0; //익지 않은 토마토 개수 체크
        Queue<Point> q = new LinkedList<>();
        for(int i=0; i<H; i++){
            for(int j=0; j<N; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<M; k++){
                    arr[i][j][k] = Integer.parseInt(st.nextToken());
                    if(arr[i][j][k] == 0) tmtCount++;
                    if(arr[i][j][k] == 1) {
                        q.add(new Point(i,j,k));
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        int ans = bfs(q, tmtCount);
        System.out.println(ans);
    }

    private static int bfs(Queue<Point> q, int tmtCount) {

        int dayCount = -1;

        while(!q.isEmpty()){
            dayCount++;
            int size = q.size();
            for(int t=0; t<size; t++){
                Point p = q.poll();
                for(int i=0; i<6; i++){
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];
                    int nh = p.h + dh[i];
                    if(nr>=0 && nr<N && nc>=0 && nc<M && nh>=0 && nh<H && !visited[nh][nr][nc]){
                        if(arr[nh][nr][nc] == 0){
                            q.add(new Point(nh, nr, nc));
                            visited[nh][nr][nc] = true;
                            tmtCount--;
                        }
                    }
                }
            }
        }
        return tmtCount == 0 ? dayCount : -1;
    }
}
