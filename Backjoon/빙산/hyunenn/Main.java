import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Point> Glaciers = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0) Glaciers.add(new Point(i, j)); // 초기 빙하
            }
        }

        int time = 0;
        while(true) {
            // 빙산이 분리됬는지 확인
            if(Glaciers.isEmpty()) {
                time = 0;
                break;
            }
            if(!checkDivide()) break;
            // 분리안됬으면, 빙산 녹이기 진행
            meltGlacier();
            time++;
        }
        System.out.println(time);
    }

    public static boolean checkDivide() {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        Point glacier = Glaciers.get(0);
        Q.offer(new Point(glacier.r, glacier.c));
        v[glacier.r][glacier.c] = true;

        int cnt = 1;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for (int k = 0; k < 4; k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && map[nr][nc] > 0 && !v[nr][nc]) {
                    cnt++;
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                }
            }
        }

//        System.out.println("초기 빙하 : " + Glaciers.size() + ", 탐색 빙하 수 : " + cnt);

        // 탐색한 갯수가 같으면 아직 하나의 빙하이므로 녹이기 필요
        if(cnt == Glaciers.size()) return true;
            // 아닐 경우, 종료
        else return false;
    }

    public static int[][] copyMap() {
        int[][] tmp = new int[N][M];
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                tmp[i][j] = map[i][j];
            }
        }
        return tmp;
    }

    public static void meltGlacier() {
        Glaciers.clear();
        // 기존 지도 복사해둔 상태로 진행
        int[][] tmp = copyMap();
        // 지도에서 빙하를 탐색
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                int curr = tmp[i][j];
                if(curr != 0) {
                    for(int k=0;k<4;k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        // 현재 위치 기준 0의 갯수만큼 값을 빼준다
                        if(inRange(nr, nc) && map[nr][nc] == 0) {
                            tmp[i][j] = Math.max(0, tmp[i][j] - 1);
                        }
                    }
                }
            }
        }
        map = tmp;
        // 현재 살아있는 빙하의 총 갯수는?
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(map[i][j] != 0) {
                    Glaciers.add(new Point(i, j));
                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
