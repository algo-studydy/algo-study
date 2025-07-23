import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int r, c, dist, magic;
        Point(int r, int c, int dist, int magic) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.magic = magic;
        }
    }
    static int N, M;
    static int[][] map;
    static int ans = Integer.MAX_VALUE;
    static int startR, startC, endR, endC;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken());
        startC = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        endR = Integer.parseInt(st.nextToken());
        endC = Integer.parseInt(st.nextToken());
        startR--; startC--; endR--; endC--;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 탐색을 진행하는데, 마법 지팡이는 한번 사용 가능하니, 벽인 지역을 지나가면 magic 이 0으로 바뀜
        find();
        // 그래서 도달 가능한 최소 dist 처리
        // 탈출이 불가능하다면 -1 로 정답 처리
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void find() {
        Queue<Point> Q = new ArrayDeque<>();
        boolean[][][] v = new boolean[N][M][2];
        Q.offer(new Point(startR, startC, 0, 1));
        v[startR][startC][1] = true;
        // 지팡이를 보유하고 있을때와 없을때를 구분해서 방문배열 탐색
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            // 도달한 지역이 현재 종료 지점이라면 값을 갱신후에 바로 종료
            if(p.r == endR && p.c == endC) {
                ans = Math.min(ans, p.dist);
                break;
            }
            for(int k=0;k<4;k++) {
                int nr = p.r + dr[k];
                int nc = p.c + dc[k];
                if(inRange(nr, nc)) {
                    // 범위 안에 있는데 현재 위치가 벽일 경우, 지팡이를 사용해 통과함
                    if(map[nr][nc] == 1 && p.magic != 0) {
                        v[nr][nc][0] = true;
                        Q.offer(new Point(nr, nc, p.dist + 1, 0));
                    }
                    // 빈 칸이면, 그냥 중복체크하고 진행
                    else if(map[nr][nc] == 0 && !v[nr][nc][p.magic]) {
                        v[nr][nc][p.magic] = true;
                        Q.offer(new Point(nr, nc, p.dist + 1, p.magic));
                    }

                }
            }
        }
    }

    public static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }
}
