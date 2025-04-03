import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] arr;
    static final int INF = 100_000*(100-1)+1;
    
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(arr[i], INF);
            arr[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = Math.min(c, arr[a][b]);
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(arr[i][j] == INF ? 0 : arr[i][j]).append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void floydWarshall() {
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    arr[a][b] = Math.min(arr[a][b], arr[a][k] + arr[k][b]);
                }
            }
        }
    }
}
