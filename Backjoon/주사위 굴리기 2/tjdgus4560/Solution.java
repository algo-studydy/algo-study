import java.util.*;

public class Main {
    static int[][] map;
    static int[][] scoreMap;
    static boolean[][] visited;
    static int[] dr = {0,1,0,-1}; // 동 남 서 북
    static int[] dc = {1,0,-1,0};
    static int N, M, K;

    public static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        map = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        } //입력끝

        // 점수지도 생성
        scoreMap = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(!visited[i][j]) scoreCalc(i,j,map[i][j]);
            }
        }

        // K 번 이동하면서 점수 계산
        int score  = 0;
        int[] dice = {2,4,1,3,5,6};
        int d = 0; //방향
        int r = 0;
        int c = 0;
        for(int i=0; i<K; i++){
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr<0 || nr>=N || nc<0 || nc>=M){
                d = (d + 2) % 4;
            }

            // 한칸이동
            r += dr[d];
            c += dc[d];
            dice = diceMove(dice, d);

            // 아래칸 점수획득
            score += scoreMap[r][c];

            // 이동방향변화
            int A = dice[5];
            int B = map[r][c];
            if(A > B){
                d = (d+1)%4;
            }else if(A < B){
                d = (d+3)%4;
            }
        }

        System.out.println(score);
    }


    private static void scoreCalc(int r, int c, int num) {
        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> list = new ArrayList<>();

        q.add(new Point(r,c));
        list.add(new Point(r,c));
        visited[r][c] = true;

        while(!q.isEmpty()){
            Point p = q.poll();

            for(int i=0; i<4; i++){
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if(nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc] && map[nr][nc] == num){
                    q.add(new Point(nr, nc));
                    list.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
        int score = num * list.size();
        for(Point p : list){
            scoreMap[p.r][p.c] = score;
        }
    }

    private static int[] diceMove(int[] dice, int d){
        // d : 0동 1남 2서 3북
        int[] tmp = new int[dice.length];

        int[][] directionMap = {
                {0, 5, 1, 2, 4, 3}, // 동
                {5, 1, 0, 3, 2, 4}, // 남
                {0, 2, 3, 5, 4, 1}, // 서
                {2, 1, 4, 3, 5, 0}  // 북
        };

        for (int i = 0; i < 6; i++) {
            tmp[i] = dice[directionMap[d][i]];
        }

        return tmp;
    }


}
