package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, R, C;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][][] v;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        map = new char[N][N];
        v = new boolean[N][N][4];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int cnt = 0;

        int dir = 1;
        while (true) {
            if (v[R][C][dir]) {
                System.out.println(-1);
                return;
            }

            v[R][C][dir] = true;

            int nr = R + dr[dir];
            int nc = C + dc[dir];

            // 일단 밖으로 나갈 수 있다면 바로 종료
            if (!inRange(nr, nc)) {
                cnt++; break;
            }

            // 이동 불가능한다면, 반시계방향 회전
            int ch = 0;
            while(ch < 4) {
                if(!inRange(nr, nc)) break;

                if (map[nr][nc] == '#') {
                    if (dir == 0) dir = 3;
                    else dir--;

                    // 이동 방향 변경
                    nr = R + dr[dir];
                    nc = C + dc[dir];
                } else break;

                ch++;
            }

            if(!inRange(nr, nc)) {
                cnt++; break;
            }

            // 이동이 가능해졌거나, 가능하다면 이동
            if (map[nr][nc] == '.') {
                // 오른쪽에 벽이 없다면, 시게방향 + 1
                if(!check(nr, nc, dir)) dir = (dir+1) % 4;
                R = nr; C = nc;
            }

            cnt++;
//            System.out.println("현재 위치 : " + R + " " + C + " , " + dir);
//            System.out.println(cnt);

        }

        System.out.println(cnt);

    }

    private static boolean check(int r, int c, int dir) {
        if(dir == 0 && map[r][c+1] == '#') return true;
        else if(dir == 1 && map[r+1][c] == '#') return true;
        else if(dir == 2 && map[r][c-1] == '#') return true;
        else if(dir == 3 && map[r-1][c] == '#') return true;
        return false;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    /**
     * 1. 바라보는 방향으로 이동이 불가능하면 반시계방향 1번
     * 2. 바라보는 방향으로 이동 가능한 경우
     * - 탈출 가능하면 탈출
     * - 이동한 기준 오른쪽에 벽이 있다면 한칸 전진
     * - 이동한 기준 오른쪽에 벽이 없다면 시계방향 회전 후 한칸 전진
     */
}
