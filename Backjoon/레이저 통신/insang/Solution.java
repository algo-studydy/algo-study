import java.util.*;

public class BOJ6087레이저통신 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int h, w;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        w = sc.nextInt();
        h = sc.nextInt();

        char[][] map = new char[h][w];
        LinkedList<int[]> list = new LinkedList<>();

        for(int i = 0; i < h; i++){
            map[i] = sc.next().toCharArray();
            for(int j = 0; j < w; j++){
                if(map[i][j] == 'C') list.offer(new int[] {i, j});
            }
        }

        int[] start = list.poll();
        int[] end = list.poll();

        // 방향별 최소 거울 개수 저장
        int[][][] visited = new int[h][w][4];
        for (int[][] row : visited) {
            for (int[] dir : row) {
                System.out.println(Arrays.toString(dir));
                Arrays.fill(dir, Integer.MAX_VALUE);
            }
        }

        Queue<int[]>q = new ArrayDeque<>();
        // 시작점에서 4방향 모두로 출발할 수 있으므로 큐에 모두 삽입
        for (int d = 0; d < 4; d++) {
            visited[start[0]][start[1]][d] = 0;
            q.offer(new int[]{start[0], start[1], d, 0});  // [행, 열, 방향, 거울 개수]
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dir = cur[2], cnt = cur[3];  // 설치한 거울 개수

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(check(nr,nc) && map[nr][nc] != '*'){
                    int nextMirror = cnt + (dir == d ? 0 : 1);

                    // 이전보다 더 적은 거울로 도달 가능할 때만 큐에 추가
                    if (visited[nr][nc][d] > nextMirror) {
                        visited[nr][nc][d] = nextMirror;
                        q.offer(new int[]{nr, nc, d, nextMirror});
                    }
                }
            }
        }

        // 도착점의 네 방향 중 최소 거울 개수를 정답으로 출력
        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, visited[end[0]][end[1]][d]);
        }

        System.out.println(answer);
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < h && nc >= 0 && nc < w;
    }
}
