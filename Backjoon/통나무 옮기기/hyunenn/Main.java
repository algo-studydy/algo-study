import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, dir, dist;

        Point(int r, int c, int dir, int dist) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.dist = dist;
        }
    }

    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int startR, startC, sDir, endR, endC, eDir;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        boolean f = false, s = false;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B' && !f) {
                    // 세로 통나무
                    if (i + 1 < N && map[i + 1][j] == 'B') {
                        startR = i + 1;
                        startC = j;
                        sDir = 0;
                        f = true;
                    }
                    // 가로 통나무
                    else if (j + 1 < N && map[i][j + 1] == 'B') {
                        startR = i;
                        startC = j + 1;
                        sDir = 1;
                        f = true;
                    }
                }
                if (map[i][j] == 'E' && !s) {
                    if (i + 1 < N && map[i + 1][j] == 'E') {
                        endR = i + 1;
                        endC = j;
                        eDir = 0;
                        s = true;
                    } else if (j + 1 < N && map[i][j + 1] == 'E') {
                        endR = i;
                        endC = j + 1;
                        eDir = 1;
                        s = true;
                    }
                }
            }
        }

        // bfs 로 탐색을 해야 하는데, 그 전에 B, E를 다 삭제
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if (map[i][j] == 'B' || map[i][j] == 'E') map[i][j] = '0';
            }
        }
        bfs();

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    static void bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][][] v = new boolean[N][N][2];
        Q.offer(new Point(startR, startC, sDir, 0));

        while (!Q.isEmpty()) {
            Point p = Q.poll();
            // 종료 조건 설정
            if (p.r == endR && p.c == endC && p.dir == eDir) {
                answer = Math.min(answer, p.dist);
                break;
            }

            // 1. 4방탐색 -> p.dir 이 0이냐 1이냐에 따라 분기 변경
            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if (p.dir == 0) { // 세로
                    if (!inRange(nr - 1, nc) || !inRange(nr + 1, nc) || v[nr][nc][p.dir]) continue;
                    if (map[nr - 1][nc] != '1' && map[nr][nc] != '1' && map[nr + 1][nc] != '1') {
                        v[nr][nc][p.dir] = true;
                        Q.offer(new Point(nr, nc, p.dir, p.dist + 1));
                    }
                } else if (p.dir == 1) { // 가로
                    if (!inRange(nr, nc - 1) || !inRange(nr, nc + 1) || v[nr][nc][p.dir]) continue;
                    if (map[nr][nc - 1] != '1' && map[nr][nc] != '1' && map[nr][nc + 1] != '1') {
                        v[nr][nc][p.dir] = true;
                        Q.offer(new Point(nr, nc, p.dir, p.dist + 1));
                    }
                }
            }
            // 2. 회전 후 탐색
            if (canRotate(p.r, p.c)) {
                // 회전 가능한 경우, 회전 시키고 Q에 삽입
                if (p.dir == 0 && !v[p.r][p.c][1]) {
                    v[p.r][p.c][1] = true;
                    Q.offer(new Point(p.r, p.c, 1, p.dist + 1));
                } else if (p.dir == 1 && !v[p.r][p.c][0]) {
                    v[p.r][p.c][0] = true;
                    Q.offer(new Point(p.r, p.c, 0, p.dist + 1));
                }
            }
        }
    }

    static boolean canRotate(int r, int c) {
        // 범위 안에 있으면서, 1이 없어야 회전 가능
        if (r >= 1 && r < N - 1 && c >= 1 && c < N - 1) {
            for (int i = r - 1; i <= r + 1; i++) {
                for (int j = c - 1; j <= c + 1; j++) {
                    if (map[i][j] == '1') return false;
                }
            }
            return true;
        }
        return false;
    }

    static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
    // 4방 탐색 + 중심점 기준 회전에 대한 모든 경우의 최소 경우를 구하는 bfs 문제
    // 통나무 중심 기준 3x3 정사각형에 1이 있으면 회전이 불가능하다.
    // 통나무 좌표를 list 에 저장? 변수로 저장?
    // 회전 기준 : firstR, C 는 (0,1) (1,0) 중간값은 (1,1) , (2,1) (1,2)
    // 회전 조건 : 중앙값에서 r,c 가 -1 +1 범위가 지도안에 있고, 현재 설정한 사각형 범위 내에 1이 없어야 함

}