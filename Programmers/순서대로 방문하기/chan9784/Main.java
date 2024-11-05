import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> spot = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N, M;
    static int cnt = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 2;
            spot.add(new int[] {x, y});
        }
        int[] t = spot.get(0);
        visited[t[0]][t[1]] = true;
        dfs(t[0], t[1], 1);
        System.out.println(cnt);
    }
    
    public static void dfs(int x, int y, int index) {
        if(spot.get(index)[0] == x && spot.get(index)[1] == y) {
            if(index == spot.size() - 1) {
                cnt += 1;
                return;
            } else {
                index++;
            }
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx > N || nx < 1 || ny > N || ny < 1 || visited[nx][ny] || map[nx][ny] == 1) {
                continue;
            }
            if(map[nx][ny] == 2 && spot.get(index)[0] != nx && spot.get(index)[1] != ny) {
                continue;
            }
            visited[nx][ny] = true;
            dfs(nx, ny, index);
            visited[nx][ny] = false;
        }
    }
}
