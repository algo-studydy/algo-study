
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, num;
        Point(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
    static int N;
    static List<Point> list = new ArrayList<>();
    static int[][] map;
    static int[][] dp;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                list.add(new Point(i, j, map[i][j]));
            }
        }

        list.sort((a, b) -> a.num - b.num);

        for(Point p : list) {
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                if(map[nr][nc] > map[p.r][p.c]) {
                    dp[nr][nc] = Math.max(dp[nr][nc], dp[p.r][p.c] + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                max = Math.max(max, dp[i][j]);
            }
        }

        System.out.println(++max);
    }



}
