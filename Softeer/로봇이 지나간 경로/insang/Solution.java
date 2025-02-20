import java.util.*;

public class SOF6275로봇이지나간경로 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static char[] direction = {'>', 'v', '<', '^'};
    static int h, w, dir, roadCnt;
    static char[][] map;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        h = sc.nextInt();
        w = sc.nextInt();
        map = new char[h][w];

        for(int i = 0; i < h; i++){
            char[] temp = sc.next().toCharArray();
            for(int j = 0; j < w; j++){
                map[i][j] = temp[j];
                if(map[i][j] == '#') roadCnt++;
            }
        }

        // 주어진 입력은 로봇의 이동 경로이므로, 경로가 끊길 일이 없고 하나로 이어져 있음
        // 따라서, 경로의 시작이나 끝 지점만 찾으면 됨.
        search();

    }

    private static void search() {
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                // 해당 지점이 경로의 시작점 or 끝점이라면
                if(map[i][j] == '#' && check(i, j)) {
                    System.out.println((i+1) + " " + (j+1));
                    System.out.println(direction[dir]);
                    go(i, j);
                    return;
                }
            }
        }
    }

    // 시작점 or 끝점 찾는 함수
    private static boolean check(int r, int c) {
        int cnt = 0;
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;

            if(map[nr][nc] == '#') {
                cnt++;
                dir = i;
            }
        }
        return cnt <= 1;  // 4방 탐색 후 연결된 경로가 2개 이상이면 false
    }

    private static void go(int r, int c) {
        int cnt = 1;  // 로봇이 이동한 길이

        // 로봇이 이동한 길이가 전체 경로의 길이보다 작으면 루프 실행
        while(cnt < roadCnt){
            // 앞으로 2칸 전진
            r += dr[dir] * 2;
            c += dc[dir] * 2;
            cnt += 2;  // 이동 길이 추가
            System.out.print("A");

            // 진행 가능한 방향으로 방향 전환, 직진이 가능하면 방향 전환 X

            // 오른쪽으로 방향 전환
            int rr = r + dr[(dir + 1) % 4];
            int rc = c + dc[(dir + 1) % 4];

            // 왼쪽으로 방향 전환
            int lr = r + dr[(dir + 3) % 4];
            int lc = c + dc[(dir + 3) % 4];

            // 오른쪽으로 방향 전환이 가능한 경우, 방향을 오른쪽으로 변경 후 R 출력
            if(rr >= 0 && rr < h && rc >= 0 && rc < w && map[rr][rc] == '#'){
                dir = (dir + 1) % 4;
                System.out.print("R");
            }
            // 왼쪽으로 방향 전환이 가능한 경우, 방향을 왼쪽으로 변경 후 L 출력
            else if(lr >= 0 && lr < h && lc >= 0 && lc < w && map[lr][lc] == '#'){
                dir = (dir + 3) % 4;
                System.out.print("L");
            }
        }
    }
}
