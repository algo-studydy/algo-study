package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, dir, w, idx;

        Point(int r, int c, int dir, int w, int idx) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.w = w;
            this.idx = idx;
        }
    }

    static int N, M, T;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static List<Point> list;
    static StringTokenizer st;

    // 순서대로 탐색을 진행함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        map = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            String dir = st.nextToken();
            int w = Integer.parseInt(st.nextToken());

            // 0. 방향별 값에 대한 구슬을 list 에 삽입
            switch (dir) {
                case "U":
                    list.add(new Point(r, c, 0, w, i));
                    break;
                case "R":
                    list.add(new Point(r, c, 1, w, i));
                    break;
                case "D":
                    list.add(new Point(r, c, 2, w, i));
                    break;
                case "L":
                    list.add(new Point(r, c, 3, w, i));
                    break;
            }
        }

        while (T > 0) {
            List<Point> newList = new ArrayList<>();
            int[][] newMap = new int[N][N];
            for (Point p : list) {
                int nr = p.r + dr[p.dir];
                int nc = p.c + dc[p.dir];
                // 범위를 벗어나는 경우, 방향만 전환
                if (!inRange(nr, nc)) {
                    int dir = (p.dir + 2) % 4;
                    newList.add(new Point(p.r, p.c, dir, p.w, p.idx));
                    newMap[p.r][p.c]++;
                    continue;
                }
                // 아니면 한칸 이동 후 지도 추가
                newList.add(new Point(nr, nc, p.dir, p.w, p.idx));
                newMap[nr][nc]++;
            }

            List<Point> resultList = new ArrayList<>();
            // newMap 기준 1이상 인 경우, w 는 다 합치고, idx 가 가장 큰 값의 dir 을 따라감
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (newMap[i][j] == 0) continue;

                    int sumWeight = 0, dir = 0, idx = Integer.MIN_VALUE;
                    for (Point p : newList) {
                        if (p.r == i && p.c == j) {
                            sumWeight += p.w;
                            if (p.idx > idx) {
                                idx = p.idx;
                                dir = p.dir;
                            }
                        }
                    }

                    resultList.add(new Point(i, j, dir, sumWeight, idx));
                }
            }

            list = new ArrayList<>(resultList);

//            System.out.println("T번째 : " + T);
//            for(Point p : list) {
//                System.out.println("r: " + p.r + " , c : " + p.c + " dir : " + p.dir + " w : " + p.w + " idx: " + p.idx);
//            }
            T--;
        }

        int max = 0;
        for(Point p : list) {
            max = Math.max(max, p.w);
        }

        System.out.println(list.size() + " " + max);
    }


    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

/**
 * 0. 구슬은 (r, c, 방향, 무게)로 주어짐. 그리고 주어지는 순서대로 번호를 매긴다.
 * 1. T 시간 마다, 구슬들은 정해진 방향대로 움직인다. 벽을 만나면, 방향만 바꿈
 * 1-1. 만약에, 구슬끼리 부딪힌다면 무게는 합치고 가장 큰 번호의 방향을 따라가도록 한다.
 * 2. 이와 같은 내용을 계속 반복한다.
 */