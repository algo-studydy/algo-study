import java.util.*;

public class BOJ16509장군 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[] Dr = {1, 1, -1, -1};  // 대각선
    static int[] Dc = {1, -1, -1, 1};
    static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r1 = sc.nextInt();
        int c1 = sc.nextInt();
        int r2 = sc.nextInt();
        int c2 = sc.nextInt();
        int answer = -1;

        int[][] map = new int[10][9];
        map[r2][c2] = 1;  // 왕의 위치 1로 변경

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r1, c1, 0});
        visited = new boolean[10][9];
        visited[r1][c1] = true;

        outer:
        while(!q.isEmpty()){
            int[] cur = q.poll();

            if(cur[0] == r2 && cur[1] == c2){
                answer = cur[2];
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];

                // 상하좌우 전진 후, 범위 체크 & 방해물 체크
                if (!check(nr, nc) || map[nr][nc] == 1) continue;

                // 같은 방향으로 대각선 이동 (대각선은 2방향)
                for (int j = 0; j < 2; j++) {
                    int d = (i + 3 + j) % 4;

                    for (int k = 1; k <= 2; k++) {
                        int Nr = nr + Dr[d] * k;
                        int Nc = nc + Dc[d] * k;

                        // 대각선으로 한칸 이동 후, 범위 체크 & 방해물 체크
                        if (k == 1 && check(Nr, Nc) && map[Nr][Nc] == 1) break;
                            // 대각선으로 두칸 이동 후, 방문하지 않은 곳이면 큐에 담음
                        else if (k == 2 && check(Nr, Nc) && !visited[Nr][Nc]) {
                            // 이동 완료 후, 왕 발견 시 정답 갱신, while문 종료
                            if (Nr == r2 && Nc == c2) {
                                answer = cur[2] + 1;
                                break outer;
                            }

                            q.offer(new int[]{Nr, Nc, cur[2] + 1});
                            visited[Nr][Nc] = true;
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static boolean check(int r, int c){
        return r >= 0 && r < 10 && c >= 0 && c < 9;
    }
}
