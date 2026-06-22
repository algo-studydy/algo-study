import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1001 {
    static int N, M;
    static int[][] adjMatrix;
    static boolean[] visited;
    static int ans;

    static void go(int v) {
        for (int i=1;i<=N;i++) {
            if (visited[i]) continue;
            if (adjMatrix[v][i] == 0) continue;

            visited[i] = true;
            ans += 1;
            go(i);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjMatrix = new int[N+1][N+1];
        visited = new boolean[N+1];

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjMatrix[a][b] = 1;
            adjMatrix[b][a] = 1;
        }

        visited[1] = true;
        go(1);

        System.out.print(ans);
    }
}
