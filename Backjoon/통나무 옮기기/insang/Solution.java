import java.util.*;

public class Main {
    static int n;
    static char[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new char[n][n];
        int answer = 0;

        ArrayList<int[]> tree = new ArrayList<>();  // 통나무의 좌표를 담을 리스트
        ArrayList<int[]> end = new ArrayList<>();  // 도착 지점의 좌표를 담을 리스트

        for(int i = 0; i < n; i++){
            char[] arr = sc.next().toCharArray();
            for(int j = 0; j < n; j++){
                map[i][j] = arr[j];
                if(arr[j] == 'B') tree.add(new int[] {i, j});
                if(arr[j] == 'E') end.add(new int[] {i, j});
            }
        }

        // 통나무의 중심점과 방향(가로, 세로)만 알면 나머지 두 점도 알 수 있음
        int[] mid = tree.get(1);
        int dir = tree.get(0)[0] == tree.get(1)[0] ? 0 : 1;  // 0 : 가로, 1 : 세로

        // 도착점의 중심점과 방향을 통해 도착 여부 확인
        int[] endMid = end.get(1);
        int endDir = end.get(0)[0] == end.get(1)[0] ? 0 : 1;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][n][2];
        q.offer(new int[] {mid[0], mid[1], dir, 0});
        visited[mid[0]][mid[1]][dir] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            // 도착점에 도착 시 정답 출력 후, 반복문 종료
            if(cur[0] == endMid[0] && cur[1] == endMid[1] && cur[2] == endDir){
                answer = cur[3];
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                // 상하좌우 이동이 가능한지 체크
                if (check(nr, nc, cur[2]) && !visited[nr][nc][cur[2]]) {
                    visited[nr][nc][cur[2]] = true;
                    q.offer(new int[]{nr, nc, cur[2], cur[3]+1});
                }
            }

            // 회전이 가능한지 체크
            if (rotationCheck(cur[0], cur[1]) && !visited[cur[0]][cur[1]][1 - cur[2]]) {
                visited[cur[0]][cur[1]][1 - cur[2]] = true;
                q.offer(new int[]{cur[0], cur[1], 1-cur[2], cur[3]+1});
            }
        }

        System.out.println(answer);
    }

    // 상하좌우 이동이 가능한지 확인하는 메서드
    static boolean check(int nr, int nc, int d) {
        if(nr < 0 || nr >= n || nc < 0 || nc >= n) return false;

        if (d == 0) { // 가로
            return nc - 1 >= 0 && nc + 1 < n
                    && map[nr][nc-1] != '1' && map[nr][nc] != '1' && map[nr][nc+1] != '1';
        }

        else { // 세로
            return nr - 1 >= 0 && nr + 1 < n
                    && map[nr-1][nc] != '1' && map[nr][nc] != '1' && map[nr+1][nc] != '1';
        }
    }

    // 회전이 가능한지 검사하는 메서드
    private static boolean rotationCheck(int r, int c) {
        // 중앙 좌표 기준으로 3x3 영역 탐색
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                // 격자를 벗어나거나, 다른 나무가 존재하면 false 리턴
                if (i < 0 || j < 0 || i >= n || j >= n || map[i][j] == '1') {
                    return false;
                }
            }
        }
        return true;
    }
}
