
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
    static StringTokenizer st;
    static int N, M, T;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int[][] count;
    static int[][] nextCount;
    static List<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        map = new int[N][N];
        count = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 현재 시작 위치 설정
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            list.add(new Point(r, c));
            count[r][c]++;
        }

        // 시간만큼 반복
        while(T > 0) {
            nextCount = new int[N][N];
            List<Point> newList = new ArrayList<>();
            // 현재 count 위치에서 4방 탐색을 진행하고, 가장 큰 값으로 이동
            for(Point p : list) {
                int nextR = 0, nextC = 0, num = 0;
                // 4방 탐색에서 가장 큰값 저장
                for(int k=0;k<4;k++) {
                    int nr = p.r + dr[k];
                    int nc = p.c + dc[k];

                    if(!inRange(nr, nc)) continue;
                    if(map[nr][nc] > num) {
                        num = map[nr][nc];
                        nextR = nr; nextC = nc;
                    }
                }
                nextCount[nextR][nextC]++;
                newList.add(new Point(nextR, nextC));
            }

            list = new ArrayList<>();
            count = new int[N][N];
            for(Point p : newList) {
                if(nextCount[p.r][p.c] == 1) {
                    list.add(p);
                    count[p.r][p.c] = 1;
                }
            }

            count = nextCount;
            T--;
        }

//        printMap();
        System.out.println(countMap());
    }

    private static int countMap() {
        int cnt = 0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                if(count[i][j] == 1) cnt++;
            }
        }
        return cnt;
    }

    private static void printMap() {
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(count[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
/** count 맵과 nextCount 맵으로 이동 위치를 표시한다?
 * 4 3 1
 * 1 2 2 3
 * 3 5 10 15
 * 3 8 11 2
 * 4 5 4 4
 * 2 2
 * 3 4
 * 4 2
 */