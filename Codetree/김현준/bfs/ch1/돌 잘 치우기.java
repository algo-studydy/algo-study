package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


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
    static Queue<Point> Q = new ArrayDeque<>();
    static List<Point> list = new ArrayList<>();
    static List<Point> chooses;
    static List<Point> stones;
    static int N, K, M;
    static boolean[][] v;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); // 시작위치 수
        M = Integer.parseInt(st.nextToken()); // 지울 갯수
        map = new int[N][N];
        chooses = new ArrayList<>();
        stones = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) stones.add(new Point(i, j));
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            list.add(new Point(r, c));
        }

        // 1 갯수중에 M 개 고르기
        DFS(0);
        System.out.println(max);
    }

    private static void DFS(int idx) {
        // basis
        if(chooses.size() == M) {
            max = Math.max(max, calculate());
            return;
        }
        // inductive
        for(int i = idx; i < stones.size(); i++) {
            chooses.add(stones.get(i));
            DFS(i + 1);
            chooses.remove(chooses.size() - 1);
        }
    }

    private static int calculate() {
        int[][] tmp = new int[N][N];
        v = new boolean[N][N];
        int cnt = list.size();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        for(Point p : chooses) tmp[p.r][p.c] = 0;

        for(Point p : list) {
            Q.offer(p);
            v[p.r][p.c] = true;
        }

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(canGo(nr, nc, tmp)) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc));
                    cnt++;
                }
            }
        }

        return cnt;
    }

    private static boolean canGo(int r, int c, int[][] tmp) {
        if(!inRange(r, c)) return false;

        if(tmp[r][c] == 1 || v[r][c]) return false;

        return true;
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
