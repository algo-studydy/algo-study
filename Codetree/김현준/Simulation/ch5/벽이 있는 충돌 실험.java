
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, dir;

        Point(int r, int c, int dir) {
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static int T, N, M;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static List<Point> list;
    static StringTokenizer st;

    // 순서대로 탐색을 진행함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t=0;t<T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            list = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken()) - 1;
                String dir = st.nextToken();
                if (dir.equals("U")) list.add(new Point(r, c, 0));
                else if (dir.equals("R")) list.add(new Point(r, c, 1));
                else if (dir.equals("D")) list.add(new Point(r, c, 2));
                else if (dir.equals("L")) list.add(new Point(r, c, 3));
            }
            // 1. 리스트에 이동할 구슬들 저장
            // 2. 구슬들 순서대로 이동 시킨다
            // 3. 이동하고, 현재 그 자리가 2 이상인 경우 그 자리 구슬 전부 제거
            // 0. 위 과정을 T번 반복
            for(int move=0;move<2*N;move++) {
                int[][] nextMap = new int[N][N];
                List<Point> newList = new ArrayList<>();
                for (Point p : list) {
                    int dir = p.dir;
                    int nr = p.r + dr[p.dir];
                    int nc = p.c + dc[p.dir];
                    if (!inRange(nr, nc)) {
                        dir = (p.dir + 2) % 4;
                        nextMap[p.r][p.c]++;
                        newList.add(new Point(p.r, p.c, dir));
                        continue;
                    }

                    nextMap[nr][nc]++;
                    newList.add(new Point(nr, nc, dir));
                }

                list = new ArrayList<>();

                for (Point p : newList) {
                    if (nextMap[p.r][p.c] == 1) list.add(p);
                }
                map = nextMap;
            }
            System.out.println(list.size());
        }
    }

    private static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

/**
 * 20 * 20 * 8 * 100
 */