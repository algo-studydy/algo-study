import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int N,Q; //N : 2^N크기의 map, Q : 파이어스톰 수행수
    static int[][] map;
    static int[] L; // 마법들
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static boolean[][] visited;

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Q = sc.nextInt();
        int size = 1 << N;
        map = new int[size][size];
        L = new int[Q];

        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int i=0; i<Q; i++){
            L[i] = sc.nextInt();
        }


        for(int l : L){
            // 1. 회전
            rotate(1 << l);

            // 2. 얼음제거
            melt();
        }

        // 3. Q번실행후 얼음수체크, 덩어리크기체크
        int totalIce = 0;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                totalIce += map[i][j];
            }
        }

        visited = new boolean[size][size];
        int max = 0;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    max = Math.max(max, bfs(i, j, size));
                }
            }
        }

        System.out.print(totalIce + " " + max);
    }

    private static void rotate(int l) {
        int size = 1 << N;

        int[][] tmp = new int[size][size];

        for (int sr=0; sr<size; sr+=l) {
            for (int sc=0; sc<size; sc+=l) {
                // 블록 내부 회전
                for (int r=0; r<l; r++) {
                    for (int c=0; c<l; c++) {
                        int fromR = sr+r;
                        int fromC = sc+c;
                        int toR = sr+c;
                        int toC = sc-r-1+l;
                        tmp[toR][toC] = map[fromR][fromC];
                    }
                }
            }
        }
        map = tmp;
//        printmap();

    }

    private static void melt() {
        int size = 1 << N;
        int[][] copy = new int[size][size];

        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (map[i][j] == 0) continue;
                int count = 0;
                for (int d=0; d<4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (nr>=0 && nr<size && nc>=0 && nc<size && map[nr][nc] > 0) {
                        count++;
                    }
                }
                copy[i][j] = (count >= 3) ? map[i][j] : map[i][j] - 1;
            }
        }

        map = copy;
    }

    private static int bfs(int r, int c, int size) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(r, c));
        visited[r][c] = true;
        int count = 1;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d=0; d<4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (nr>=0 && nr<size && nc>=0 && nc<size && !visited[nr][nc] && map[nr][nc] > 0) {
                    visited[nr][nc] = true;
                    q.add(new Point(nr, nc));
                    count++;
                }
            }
        }
        return count;
    }


//    private static void printmap() {
//        int powN = (int)Math.pow(2,N);
//        for(int i=0; i<powN; i++){
//            for(int j=0; j<powN; j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        System.out.println("---");
//        System.out.println();
//    }
}
