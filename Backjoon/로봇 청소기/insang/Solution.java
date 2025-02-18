import java.util.*;

public class Main {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int n, m, d;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int answer = 0;
        n = sc.nextInt();
        m = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        d = sc.nextInt();  // 방향
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = sc.nextInt();
            }
        }

        while(true){
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if(map[r][c] == 0) {
                map[r][c] = 2;
                answer++;
            }
            // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if(check(r, c)){
                int nr = r - dr[d];
                int nc = c - dc[d];
                // 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                if(map[nr][nc] != 1) {
                    r = nr;
                    c = nc;
                }
                // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                else break;
            }
            // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            else {
                // 3-1. 반시계 방향으로 90도 회전한다.
                d = (d + 3) % 4;
                // 3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸의 경우 한 칸 전진
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(map[nr][nc] == 0){
                    r = nr;
                    c = nc;
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int r, int c) {
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(map[nr][nc] == 0) return false;
        }
        return true;
    }
}
