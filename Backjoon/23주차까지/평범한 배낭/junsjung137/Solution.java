import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] bag = new int[N + 1][2];
        int[] dp = new int[K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            bag[i][0] = W;
            bag[i][1] = V;
        }

        for (int i = 1; i <= N; i++) {
            int w = bag[i][0];
            int v = bag[i][1];

            for (int j = K; j >= w; j--) {
                dp[j] = Math.max(dp[j], dp[j - w] + v);
            }
        }

        System.out.println(dp[K]);
    }
}
