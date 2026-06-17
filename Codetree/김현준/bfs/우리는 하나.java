
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, num;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int N, K, U, D;
    static boolean[][] v;
    static List<Point> list = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 고를 도시 갯수
        U = Integer.parseInt(st.nextToken()); // U 이상 D 이하일 경우 이동 가능
        D = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0, 0);
        System.out.println(max);
    }

    private static void DFS(int r, int c, int idx) {
        // basis
        if(idx == K) {
            max = Math.max(max, BFS());
            return;
        }
        // inductive
        for(int i=r;i<N;i++) {
            for(int j=c;j<N;j++) {
                list.add(new Point(i, j));
                if(j == N - 1) DFS(i + 1, 0, idx + 1);
                else DFS(i, j + 1, idx + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    private static int BFS() {
        Queue<Point> Q = new ArrayDeque<>();
        v = new boolean[N][N];
        int cnt = 0;
        for(Point p : list) {
            v[p.r][p.c] = true;
            Q.offer(p);
            cnt++;
        }

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(p.r, p.c, nr, nc)) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static boolean canGo(int r, int c, int nr, int nc) {
        if(!inRange(nr, nc) || v[nr][nc]) return false;

        int diff = Math.abs(map[nr][nc] - map[r][c]);
        if(diff < U || diff > D) return false;
        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
