import java.util.*;

public class BOJ3055탈출 {
    static char[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int n, m;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        map = new char[n][m];
        int answer = 0;

        Queue<int[]> dochi = new ArrayDeque<>();
        Queue<int[]> water = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            map[i] = sc.next().toCharArray();
            for(int j = 0; j < m; j++){
                if(map[i][j] == 'S') {
                    dochi.offer(new int[] {i, j, 0});
                    visited[i][j] = true;
                }
                else if(map[i][j] == '*') water.offer(new int[] {i, j});

            }
        }

        // while문이 한번 반복할 때, 물의 이동과 고슴도치의 이동이 동시에 이루어져야 함
        // 물이 이동하는 과정에서 고슴도치가 이동하면 안되기 때문에 둘의 이동을 분리
        // 물의 이동이 마치면 고슴도치 이동
        loop:
        // 이동 가능한 물 또는 고슴도치 둘 중 하나라도 남아있으면 반복
        while(!water.isEmpty() || !dochi.isEmpty()){
            int wSize = water.size();
            // 물 이동
            // while 한번의 반복당, 큐에 담긴 요소들의 이동이 모두 마쳐야 함
            for(int i = 0; i < wSize; i++){
                int[] cur = water.poll();
                int r = cur[0], c = cur[1];

                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if(check(nr, nc) && (map[nr][nc] == '.' || map[nr][nc] =='S')){
                        water.offer(new int[] {nr, nc});
                        map[nr][nc] = '*';
                    }
                }
            }

            // 고슴도치 이동
            int dSize = dochi.size();
            for(int i = 0; i < dSize; i++){
                int[] cur = dochi.poll();
                int r = cur[0],  c = cur[1], depth = cur[2];

                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(check(nr, nc) && !visited[nr][nc] && (map[nr][nc] == '.' || map[nr][nc] == 'D')){
                        // 비버집을 만나면 while문 탈출
                        if(map[nr][nc] == 'D') {
                            answer = depth + 1;
                            break loop;
                        }
                        dochi.offer(new int[] {nr, nc, depth + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        System.out.println(answer != 0 ? answer : "KAKTUS");
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < m;
    }
}
