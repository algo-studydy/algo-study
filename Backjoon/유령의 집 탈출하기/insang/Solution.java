import java.util.*;

public class BOJ30894유령의집탈출하기 {
    static char[][] map;
    static boolean[][][] visited;
    static int n, m, sR, sC, eR, eC, answer;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        sR = sc.nextInt()-1;
        sC = sc.nextInt()-1;
        eR = sc.nextInt()-1;
        eC = sc.nextInt()-1;
        map = new char[n][m];

        ArrayList<int[]> ghost = new ArrayList<>();

        visited = new boolean[n][m][4];
        for(int i = 0; i < n; i++){
            char[] temp = sc.next().toCharArray();
            for(int j = 0; j < m; j++){
                map[i][j] = temp[j];
                if(map[i][j] != '.' && map[i][j] != '#') {
                    ghost.add(new int[]{i, j, map[i][j] - '0'});

                    for(int k = 0; k < 4; k++){
                        visited[i][j][k] = true;
                    }
                }
            }
        }

        for(int[] cur : ghost){
            int r = cur[0], c = cur[1], dir = cur[2];
            for(int i = 0; i < 4; i++){
                int dist = (dir + i) % 4;
                int nr = r, nc = c;
                while (true) {
                    nr += dr[dist];
                    nc += dc[dist];
                    if (!check(nr, nc) || map[nr][nc] != '.') break;

                    visited[nr][nc][i] = true;
                }
            }
        }

//        for(int k = 0; k < 4; k++){
//            for(int i = 0; i < n; i++){
//                for(int j = 0; j < m; j++){
//                    System.out.print(visited[i][j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }

        Queue<int[]> sj = new ArrayDeque<>();
        sj.offer(new int[] {sR, sC, 0});
        visited[sR][sC][0] = true;

        while(!sj.isEmpty()){
            int[] cur = sj.poll();
            int r = cur[0], c = cur[1], time = cur[2];

            if(r == eR && c == eC) {
                answer = time;
                break;
            }

            for(int d = 0; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(check(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc][(time+1)%4]){
                    sj.offer(new int[] {nr, nc, time+1});
                    visited[nr][nc][(time+1)%4] = true;
                }
            }
            // 1초 뒤 현재 위치를 유령이 바라보지 않는다면 제자리에서 대기
            if(!visited[r][c][(time+1) % 4]) {
                sj.offer(new int[] {r, c, time+1});
//                visited[r][c][(time+1) % 4] = true;
            }

        }

        System.out.println(answer == 0 ? "GG" : answer);
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nr < n && nc >= 0 && nc < m;
    }
}
