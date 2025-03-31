
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 아기상어 {
    static class Point implements Comparable<Point> {
        int r, c, dist;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Point p) {
            // 우선, 거리 계산 후, 같으면 왼쪽에 있는 값 정렬
            if(this.dist == p.dist) {
                if(this.r == p.r) {
                    return this.c - p.c;
                }
                return this.r - p.r;
            }
            return this.dist - p.dist;
        }
    }
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, shark_r, shark_c, shark_size, ch;
    static int[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ch = 0;
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark_r = i;
                    shark_c = j;
                    shark_size = 2;
                }
            }
        }

        int time = 0;
//        Point p = bfs();
//        System.out.println(p.r + " " + p.c + " " + p.dist);
        map[shark_r][shark_c] = 0;
        while(true) {
            Point cur = bfs();
            if(cur.r == -1) break;
            time += cur.dist;
            shark_r = cur.r;
            shark_c = cur.c;
            ch++;
            if(ch == shark_size) {
                ch = 0;
                shark_size++;
            }
            // 이동한 다음에 현재 위치를 0으로 삭제
            map[shark_r][shark_c] = 0;
//            System.out.println(shark_r + " " + shark_c + " " + shark_size);
        }
        System.out.println(time);
        // 여기서 ch 가 shar
    }

    public static Point bfs() {
        PriorityQueue<Point> PQ = new PriorityQueue<>();
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];
        Q.offer(new Point(shark_r, shark_c, 0));
        v[shark_r][shark_c] = true;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(!inRange(nr, nc)) continue;
                if(map[nr][nc] != 0 && map[nr][nc] < shark_size)
                    PQ.offer(new Point(nr, nc, p.dist + 1));
                if(!v[nr][nc] && map[nr][nc] <= shark_size) {
                    v[nr][nc] = true;
                    Q.offer(new Point(nr, nc, p.dist + 1));
                }
            }
        }

        if(PQ.isEmpty()) return new Point(-1, -1, -1);
        else return PQ.poll();
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    // 1,2,3,4,5,6 은 해당 칸의 물고기 크기, 아기 상어 초기 크기는 2

    // 4방 탐색을 진행할 수 있는 아기 상어가 존재
    // 종료 조건 : 더 이상 먹을 수 있는 물고기가 없다
    // 0. 본인보다 작은 크기의 물고기만 먹을 수 있다.
    // 1. 먹을 수 있는 물고기가 많다면, 거리가 가장 가까운 물고기를, 그 중에서도 가장 왼쪽에 있는 물고기
    // 2. 물고기를 먹으면 해당 칸은 빈칸이 된다.
    // 3. 아기 상어의 크기는 본인의 크기만큼 물고기를 먹어야 크기가 커진다.
}
