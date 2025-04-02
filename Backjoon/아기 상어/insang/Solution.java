import java.util.*;

public class Main {
    static int[][] map;
    static int size, cnt, r, c;  // 아기상어 크기, 먹은 물고기 수, 좌표
    static int n, answer, fishCnt;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        map = new int[n][n];
        answer = 0;
        size = 2;
        cnt = 0;
        fishCnt = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = sc.nextInt();
                // 먹을 수 있는 물고기 저장
                if(map[i][j] != 9 && map[i][j] != 0) fishCnt++;
                else if(map[i][j] == 9){
                    r = i;
                    c = j;
                    map[i][j] = 0;
                }
            }
        }

        while(true){
            int[] target = bfs();

            // 먹을 수 있는 물고기가 없으면 break
            if(target[2] == 0) break;

            // 아기상어 위치 갱신
            r = target[0];
            c = target[1];
            cnt++;  // 먹은 물고기 수 갱신
            fishCnt--;  // 먹을 수 있는 물고기 갱신
            map[r][c] = 0;  // 먹은 물고기 위치 0으로 갱신

            // 사이즈만큼 물고기를 먹은 경우 아기상어 크기를 증가하고 cnt 초기화
            if(cnt == size){
                size++;
                cnt = 0;
            }

            answer += target[2];
        }

        System.out.println(answer);
    }

    private static int[] bfs() {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> fishQ = new ArrayDeque<>();
        q.offer(new int[] {r, c, 0});
        visited[r][c] = true;

        while(!q.isEmpty()){
            int[] shark = q.poll();

            for(int i = 0; i < 4; i++){
                int nr = shark[0] + dr[i];
                int nc = shark[1] + dc[i];
                if(nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] > size || visited[nr][nc]) continue;

                // 먹을 수 있는 물고기를 큐에 담음
                if(map[nr][nc] != 0 && map[nr][nc] < size) {
                    fishQ.offer(new int[] {nr, nc, (shark[2] + 1)});
                }
                q.offer(new int[] {nr, nc, shark[2] + 1});
                visited[nr][nc] = true;
            }
        }

        // 먹을 수 있는 물고기가 없으면 리턴
        if(fishQ.isEmpty()) return new int[] {0, 0, 0};

        int[] target = fishQ.poll();

        // 먹을 수 있는 물고기 후보가 담긴 큐
        while(!fishQ.isEmpty()){
            int[] cur = fishQ.poll();
            // 두 물고기를 비교해서 거리가 더 작으면 먹을 물고기 갱신
            if(cur[2] < target[2]) {
                target = cur;
            }
            // 거리가 같으면 행 비교
            else if(cur[2] == target[2]){
                if(cur[0] < target[0]) {
                    target = cur;
                }
                // 행이 같으면 열 비교
                else if(cur[0] == target[0]){
                    if(cur[1] < target[1]){
                        target = cur;
                    }
                }
            }
        }

        return target;
    }
}
