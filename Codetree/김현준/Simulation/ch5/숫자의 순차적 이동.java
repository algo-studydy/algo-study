package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


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
    static int N, M;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] map;
    static int[][] nextMap;
    static StringTokenizer st;
    // 순서대로 탐색을 진행함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = 1;
        while(M > 0) {
            nextMap = new int[N][N];
            for(int i=0;i<N;i++) {
                boolean flag = false;
                for(int j=0;j<N;j++) {
                    if(map[i][j] == idx) {
                        int num = 0, nextR = 0, nextC = 0;
                        for(int k=0;k<8;k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if(!inRange(nr, nc)) continue;

                            if(map[nr][nc] > num) {
                                num = map[nr][nc];
                                nextR = nr; nextC = nc;
                            }
                        }
                        int tmp = map[i][j];
                        map[i][j] = num;
                        map[nextR][nextC] = tmp;
                        flag = true;
                        break;
                    }
                }
                if(flag) break;
            }

            if(idx > N*N) {
                idx = 1;
                M--;
            } else idx++;
        }

        printMap();
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
}

/**
 * 20 * 20 * 8 * 100
 */