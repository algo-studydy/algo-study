import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0903 {
    static int N;
    static int[][] map;
    static boolean[] visited;
    static ArrayList<Integer> list;
    static int sum, ans;

    static void go(int curRow) {
        if (curRow == N) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int i=0;i<N;i++) {
            if (visited[i]) continue;

            sum += map[curRow][i];
            visited[i] = true;
            go(curRow+1);
            sum -= map[curRow][i];
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

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<N;c++) {
                map[r][c]=Integer.parseInt(st.nextToken());
            }
        }

        go(0);

        System.out.print(ans);
    }
}
