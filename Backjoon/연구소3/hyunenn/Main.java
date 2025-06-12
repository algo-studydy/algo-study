import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Point {
        int r, c, cnt;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }
    static int N, M;
    static List<Point> virus = new ArrayList<>();
    static List<Point> selected = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static int full;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) virus.add(new Point(i, j));
            }
        }
        // 바이러스 갯수중 M개를 활성 상태로 변경하는 것이기에, 가능한 조합을 탐색해야함
        full = check();
        if(full == 0) {
            System.out.println(0);
            return;
        }
        chooseVirus(0);
        if(answer == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer);
    }

    public static void chooseVirus(int idx) {
        // basis
        if(selected.size() == M) {
            bfs();
            return;
        }
        // inductive
        for(int i=idx;i<virus.size();i++) {
            selected.add(virus.get(i));
            chooseVirus(i + 1);
            selected.remove(selected.size() - 1);
        }
    }

    public static void bfs() {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];
        for(Point p : selected) {
            v[p.r][p.c] = true;
            Q.offer(new Point(p.r, p.c, 0));
        }


        // 현재 진행된 바이러스 퍼짐 횟수
        int virus = 0;
        int max = 0;
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 범위 안에 있고, 방문 안한 곳과 빈 칸으로 바이러스가 퍼져야함
                if(inRange(nr, nc) && !v[nr][nc] && (map[nr][nc] == 0 || map[nr][nc] == 2)) {
                    v[nr][nc] = true;
                    if(map[nr][nc] == 0) virus++;
                    max = Math.max(max, p.cnt + 1);
                    Q.offer(new Point(nr, nc, p.cnt + 1));
                }
            }

            if(virus == full) {
                answer = Math.min(answer, max);
                return;
            }
        }
//        for(int i=0;i<N;i++) {
//            for(int j=0;j<N;j++) {
//                System.out.print(copyMap[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    public static int check() {
        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
