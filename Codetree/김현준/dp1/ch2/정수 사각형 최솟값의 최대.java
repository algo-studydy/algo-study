
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = map[0][0];

        for(int j = 1; j < N; j++) {
            dp[0][j] = Math.min(dp[0][j-1], map[0][j]);
        }

        for(int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i-1][0], map[i][0]);
        }

        for(int i=1;i<N;i++) {
            for(int j=1;j<N;j++) {
                dp[i][j] = Math.max(
                        Math.min(dp[i-1][j], map[i][j]),
                        Math.min(dp[i][j-1], map[i][j])
                );
            }
        }

        System.out.println(dp[N-1][N-1]);

    }


}
