
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 아기상어 {
    static class Point implements Comparable<Point> {
        int r, c, dist;
        Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        // 1. 거리 기준 2. 행 기준 3. 열 기준
        @Override
        public int compareTo(Point p) {
            if(this.dist == p.dist) {
                if(this.r == p.r) {
                    return this.c - p.c;
                }
                return this.r - p.r;
            }
            return this.dist - p.dist;
        }
    }
    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int sharkR, sharkC, sharkSize;
    static int[][] map;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    sharkR = i;
                    sharkC = j;
                    sharkSize = 2;
                }
            }
        }

        // 초기 상어는 2, 자기보다 큰 물고기가 있는 곳은 못 지나감.
        // 1. 시간은 거리만큼 추가하고, 상어 위치는 물고기를 먹은 자리로 이동한다.
        // 2. 먹은 물고기 자리는 빈칸이 되고, 자신의 크기와 같은 물고기 수를 먹으면 크기가 1 증가한다.
        // 3. 더 이상 먹을 물고기가 없을때 종료한다.
        int ch = 0;
        int time = 0;
        while(true) {
            Point p = bfs();
            if(p.r == -1) break; // -1이면 먹을 물고기가 없음
            time += p.dist;
            sharkR = p.r; sharkC = p.c;
            ch++;
            if(ch == sharkSize) {
                ch = 0;
                sharkSize++;
            }
        }
        System.out.println(time);
    }

    public static Point bfs() {
        PriorityQueue<Point> PQ = new PriorityQueue<>();
        Queue<Point> Q = new ArrayDeque<>();
        map[sharkR][sharkC] = 0; // 상어 위치 0으로 만듦
        boolean[][] v = new boolean[N][N];
        Q.offer(new Point(sharkR, sharkC, 0));
        v[sharkR][sharkC] = true;

        // 탐색은 빈 곳은 Q에 넣고, 상어보다 작은 물고기를 만나면 그 자리는 Q에 넣지말고 PQ에 삽입
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                // 범위를 벗어나거나, 탐색한 지역이면 무시, 상어보다 큰 물고기 있는 곳은 못 지나감
                if(!inRange(nr, nc) || v[nr][nc] || map[nr][nc] > sharkSize) continue;
                // 작은 물고기 찾으면 그 자리 PQ 추가
                if(map[nr][nc] < sharkSize && map[nr][nc] != 0) {
                    PQ.offer(new Point(nr, nc, p.dist + 1));
                }
                v[nr][nc] = true;
                Q.offer(new Point(nr, nc, p.dist + 1));
            }
        }

        if(!PQ.isEmpty()) return PQ.poll();
        else return new Point(-1, -1, -1);
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }

    // 1,2,3,4,5,6 은 해당 칸의 물고기 크기, 아기 상어 초기 크기는 2
    // 정렬 조건은 열이 작은 거 -> 행이 작은 거

    // 4방 탐색을 진행할 수 있는 아기 상어가 존재
    // 종료 조건 : 더 이상 먹을 수 있는 물고기가 없다
    // 0. 본인보다 작은 크기의 물고기만 먹을 수 있다.
    // 1. 먹을 수 있는 물고기가 많다면, 거리가 가장 가까운 물고기를, 그 중에서도 가장 왼쪽에 있는 물고기
    // 2. 물고기를 먹으면 해당 칸은 빈칸이 된다.
    // 3. 아기 상어의 크기는 본인의 크기만큼 물고기를 먹어야 크기가 커진다.
}
