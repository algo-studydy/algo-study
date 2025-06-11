import java.util.*;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    static int N; //연구소 크기
    static int M; // 바이러스 갯수
    static int[][] map;
    static List<Point> virus = new ArrayList<>();
    static int blank=0; //빈칸의 갯수
    static int answer=Integer.MAX_VALUE;
    static Point[] selected; //조합 공식 으로 선택한 바이러스들

    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][N];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = sc.nextInt();

                if(map[i][j]==2){
                    virus.add(new Point(i,j));
                }else if(map[i][j]==0){
                    blank++;
                }
            }
        }

        //빈칸이 0개면 바로 0
        if(blank==0){
            System.out.println(0);
            return;
        }

        selected = new Point[M];
        re(0,0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void re(int count, int idx){
        if(count==M){
            bfs();
            return;
        }

        for(int i = idx; i< virus.size(); i++){
            selected[count] = virus.get(i);
            re(count+1, i+1);
        }
    }

    static void bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(int i=0; i<M; i++){
            Point p = selected[i];
            q.offer(new Point(p.r,p.c));
            visited[p.r][p.c] = true;
        }

        int time=0;
        int count=0; //빈칸 채운 갯수

        while(!q.isEmpty()){
            int size = q.size();

            for(int s=0; s<size; s++){
                Point p = q.poll();

                for(int d=0; d<4; d++){
                    int nr=p.r+dr[d];
                    int nc=p.c+dc[d];

                    if(nr>=0 && nr<N && nc>=0 && nc<N){
                        if(!visited[nr][nc] && map[nr][nc]!=1){
                            visited[nr][nc] = true;
                            q.offer(new Point(nr,nc));

                            if(map[nr][nc]==0){
                                count++;
                            }
                        }
                    }
                }
            }

            time++;
            if(count==blank){
                answer = Math.min(answer,time);
                return;
            }
        }
    }
}
