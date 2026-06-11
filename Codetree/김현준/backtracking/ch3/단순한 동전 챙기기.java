package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

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
    static int startR, startC, endR, endC;
    static List<Point> list;
    static List<Point> comb;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static char[][] map;
    static boolean[] v;
    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        v = new boolean[10];
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if(map[i][j] == 'S') {
                    startR = i;
                    startC = j;
                } else if(map[i][j] == 'E') {
                    endR = i;
                    endC = j;
                } else if(map[i][j] >= '1' && map[i][j] <= '9') {
                    list.add(new Point(i, j, map[i][j] - '0'));
                }
            }
        }

        list.sort((a, b) -> a.num - b.num);
        comb = new ArrayList<>();
        choose(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void choose(int cnt, int idx) {
        // basis
        if(cnt == 3) {
            min = Math.min(min, calculate());
            return;
        }
        // inductive
        for(int i=idx;i<list.size();i++) {
            Point p = list.get(i);
            comb.add(p);
            choose(cnt + 1, i + 1);
            comb.remove(comb.size() - 1);
        }
    }

    private static int calculate() {
        int sum = 0;
        int sr = startR, sc = startC;
        for(Point p : comb) {
            sum += Math.abs(sr - p.r) + Math.abs(sc - p.c);
            sr = p.r; sc = p.c;
        }
        sum += Math.abs(sr - endR) + Math.abs(sc - endC);
        return sum;
    }

    // cnt 가 3으로 조합을 구성해서, 각 번호별 멘헤튼 거리 구하기?
}
