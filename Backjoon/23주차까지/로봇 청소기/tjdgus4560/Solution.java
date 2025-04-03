import java.util.Scanner;

public class Main {
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int[][] visit;
    static int[][] map;
    static int N,M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        map = new int[N][M];
        visit = new int[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                map[i][j] = sc.nextInt();
            }
        }
        int answer = solution(r, c, d);
        System.out.println(answer);
    }

    private static int solution(int r, int c, int d) {
        int count = 0;
        while(true){
            if(map[r][c] == 0){
                map[r][c] = 2;
                count++;
            }
            boolean check = false; //청소해야하는 유무
            for(int i=0; i<4; i++){
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(map[nr][nc] == 0) {
                        check = true;
                        break;
                    }
                }
            }
            if(check){
                //청소해야함
                for(int i=0; i<4; i++){
                    d = (d+3)%4;
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr>=0 && nr<N && nc>=0 && nc<M){
                        if(map[nr][nc] == 0) {
                            r = nr;
                            c = nc;
                            break;
                        }
                    }
                }
            }else{
                int nr = r + dr[(d+2)%4];
                int nc = c + dc[(d+2)%4];
                if(nr>=0 && nr<N && nc>=0 && nc<M){
                    if(map[nr][nc] == 1) {
                        break;
                    }else{
                        r = nr;
                        c = nc;
                    }
                }
            }
        }
        return count;
    }
}
