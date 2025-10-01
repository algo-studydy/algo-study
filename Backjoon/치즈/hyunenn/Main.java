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
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static boolean[][] airs;
    static List<Point> alls;
    static List<Point> cheeses;
    static List<Point> remains;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        alls = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) alls.add(new Point(i, j));
            }
        }

        int ans = 1;
        checkAir();
        int size = alls.size();
        // 초반에, 한번에 지워지는 경우 체크
        if(size == 0) {
            System.out.println(0);
            return;
        }
        checkCheeses();
        removeCheeses();
        // 한번 진행하고, 끝이라면 체크하고 바로 종료
        if(alls.isEmpty()) {
            System.out.println(ans);
            System.out.println(size);
            return;
        }

        // 공기 자리부터 전부 탐색하고, 만약에 공기에 맞닿아있는 치즈라면 다음 턴에 지워지는 치즈로 계산
        while(!alls.isEmpty()) {
            checkAir();
            size = alls.size();
            checkCheeses();
            removeCheeses();
            ans++;
        }

        System.out.println(ans);
        System.out.println(size);
    }

    public static void removeCheeses() {
        for(Point p : cheeses) {
            map[p.r][p.c] = 0;
        }
    }

    public static void checkCheeses() {
        cheeses = new ArrayList<>();
        remains = new ArrayList<>();
        while(!alls.isEmpty()) {
            Point p = alls.remove(0);
            boolean flag = false;
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(airs[nr][nc]) {
                    flag = true;
                    break;
                }
            }
            // flag 가 true 면 지워질 치즈
            if(flag) cheeses.add(p);
                // 아니라면, 남아있을 값
            else remains.add(p);
        }
        // 끝나면, 남은값들을 alls 에 덮어쓰기
        alls = remains;
    }

    // (0, 0)은 무조건 공기이므로, 시작점 기준으로 외각공기 탐색 시작
    public static void checkAir() {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(0, 0));
        airs = new boolean[N][M];
        Q.offer(new Point(0, 0));

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc) && map[nr][nc] == 0 && !airs[nr][nc]) {
                    airs[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
