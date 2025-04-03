import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class Point implements Comparable<Point>{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Point o) {
            if(this.r != o.r){
                return Integer.compare(this.r, o.r);
            }
            return Integer.compare(this.c, o.c);
        }
    }

    static int[][] map;
    static int N;
    static Point shark;
    static Point fish;
    static int t;
    static int[] dr ={0,1,0,-1};
    static int[] dc ={1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = new int[N][N];


        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();
                if(map[i][j] == 9){
                    shark = new Point(i,j);
                    map[i][j] = 0; //상어 위치 알아냈으니 0으로 변경
                }
            }
        }
        int size = 2; //현재 상어의 사이즈
        int eat = 0; // eat == size 가 됬을시 size를 증가시키기 위한 변수
        t=0; //전체 움직인 시간

        //bfs로 먹을거 발견하면 계속 진행 없으면 끝
        while(bfs(size)){
            map[fish.r][fish.c] = 0;
            eat++;
            if(eat == size){
                size++;
                eat=0;
            }
            shark.r = fish.r;
            shark.c = fish.c;
        }
        System.out.println(t);

    }

    private static boolean bfs(int size) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>(); //상어의 이동용 q
        PriorityQueue<Point> eatQ = new PriorityQueue<>(); //상어가 먹을 후보pq r오름차순,c오름차순 정렬
        q.add(shark);
        visited[shark.r][shark.c] = true;

        int time=0;
        while (!q.isEmpty()) {
            int qSize = q.size();

            for(int i=0; i<qSize; i++){
                Point p = q.poll();
                for(int j=0; j<4; j++){
                    int nr = p.r + dr[j];
                    int nc = p.c + dc[j];

                    if(nr >=0 && nr<N && nc>=0 && nc<N && !visited[nr][nc]){
                        if(map[nr][nc] <= size){
                            q.add(new Point(nr, nc));
                            visited[nr][nc] = true;
                            if(map[nr][nc] < size && map[nr][nc] != 0){
                                eatQ.add(new Point(nr, nc));
                            }
                        }
                    }
                }
            }
            time++;
            if(!eatQ.isEmpty()) break; //먹을거 발견했으니 탐색종료
        }

        if(!eatQ.isEmpty()){
            t+=time;
            fish = eatQ.poll();
            return true;
        }
        return false;
    }
}
