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
    static int[] dr = {3, 3, 2, -2, -3, -3, -2, 2};
    static int[] dc = {-2, 2, 3, 3, 2, -2, -3, -3};

    static int[][] checkR ={
            {1, 2}, {1, 2}, {0, 1}, {0, -1},
            {-1, -2}, {-1, -2}, {0, -1}, {0, 1}
    };
    static int[][] checkC ={
            {0, -1}, {0, 1}, {1, 2}, {1, 2},
            {0, 1}, {0, -1}, {1, -2}, {1, -2}
    };

    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int sR = sc.nextInt(); // 시작지점
        int sC = sc.nextInt();
        int eR = sc.nextInt(); // 종료지점
        int eC = sc.nextInt();
        visited = new boolean[10][9];

        bfs(sR, sC, eR, eC);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void bfs(int sR, int sC, int eR, int eC) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(sR, sC));
        visited[sR][sC] = true;

        int count =0;
        L : while(!q.isEmpty()){
            count++;

            int size = q.size();
            for(int t=0; t<size; t++){
                Point p = q.poll();

                for(int d=0; d<8; d++){
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];

                    int checkR1 = p.r + checkR[d][0];
                    int checkC1 = p.c + checkC[d][0];

                    int checkR2 = p.r + checkR[d][1];
                    int checkC2 = p.c + checkC[d][1];

                    //경계선, 방문 체크
                    if(nr>=0 && nr<10 && nc>=0 && nc<9 && !visited[nr][nc]){
                        // 이동경로 방해물 체크
                        if( (checkR1 == eR && checkC1 == eC) || (checkR2 == eR && checkC2 == eC)){
                            continue;
                        }
                        // 종료조건 체크
                        if( nr == eR && nc == eC){
                            min = count;
                            break L;
                        }

                        visited[nr][nc] = true;
                        q.add(new Point(nr, nc));
                    }
                }
            }
        }


    }
}
