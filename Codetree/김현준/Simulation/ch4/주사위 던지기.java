package codetree.시뮬레이션.격자안에서터지고떨어지는경우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, M, R, C;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static String[] dir;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 굴릴 횟수
        R = Integer.parseInt(st.nextToken()) - 1; // 시작 위치
        C = Integer.parseInt(st.nextToken()) - 1;
        map = new int[N][N];
        dir = new String[M];

        int[] first = {4, 6, 3, 1};
        int[] second = {5, 6, 2, 1};

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            dir[i] = st.nextToken();
        }

        map[R][C] = 6;
        for (int i = 0; i < M; i++) {
            int nr = 0, nc = 0;
            int tmp = 0;
            switch (dir[i]) {

                case "L": // 좌로 밀기
                    nr = R + dr[3]; nc = C + dc[3];
                    if(!inRange(nr, nc)) continue;
                    tmp = first[3];
                    for (int j = 3; j >= 1; j--) {
                        first[j] = first[j - 1];
                    }
                    first[0] = tmp;
                    // 1, 3번 인덱스 교체
                    second[1] = first[1];
                    second[3] = first[3];
                    break;
                case "R": // 우로 밀기
                    nr = R + dr[1]; nc = C + dc[1];
                    if(!inRange(nr, nc)) continue;
                    tmp = first[0];
                    for (int j = 0; j < 3; j++) {
                        first[j] = first[j + 1];
                    }
                    first[3] = tmp;
                    second[1] = first[1];
                    second[3] = first[3];
                    break;
                case "U":
                    nr = R + dr[0]; nc = C + dc[0];
                    if(!inRange(nr, nc)) continue;
                    tmp = second[3];
                    for (int j = 3; j >= 1; j--) {
                        second[j] = second[j - 1];
                    }
                    second[0] = tmp;
                    first[1] = second[1];
                    first[3] = second[3];
                    break;
                case "D":
                    nr = R + dr[2]; nc = C + dc[2];
                    if(!inRange(nr, nc)) continue;
                    tmp = second[0];
                    for (int j = 0; j < 3; j++) {
                        second[j] = second[j + 1];
                    }
                    second[3] = tmp;
                    first[1] = second[1];
                    first[3] = second[3];
                    break;
            }
            R = nr; C = nc;
            map[R][C] = first[1];

        }
        System.out.println(calculate());
    }

    private static int calculate() {
        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                cnt += map[i][j];
            }
        }
        return cnt;
    }

    private static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    // 범위를 벗어나는 행동일 경우, 그냥 무시하고 다음 행동
}
