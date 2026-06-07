import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0904 {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static int tSum;
    static int ans;

    static void go(int curIdx, int count) {
        if (count==N-1) {
            if (map[curIdx][0] == 0) return;
            int cost = map[curIdx][0];
            ans = Math.min(ans, tSum + cost);
            return;
        }

        for (int i=0;i<N;i++) {
            if (visited[i]) continue;
            if (map[curIdx][i] == 0) continue;

            visited[i] = true;
            int cost = map[curIdx][i];
            tSum += cost;
            go(i, count + 1);
            tSum -= cost;
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N];
        ans = Integer.MAX_VALUE;

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0] = true;
        go(0, 0);

        System.out.print(ans);
    }
}
