import java.io.*;
import java.util.*;

public class ct1708 {
    static int N;
    static int[][] arr;
    static long[][][] dp;

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][2];
        dp = new long[N + 1][12][10];

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= 11; j++) {
                Arrays.fill(dp[i][j], Long.MIN_VALUE);
            }
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken()); // 축구 능력
            arr[i][1] = Integer.parseInt(st.nextToken()); // 야구 능력
        }
    }

    static void go() {
        dp[0][11][9] = 0;

        for (int i = 1; i <= N; i++) {
            int soccer = arr[i][0];
            int baseball = arr[i][1];

            for (int soccerRemain = 0; soccerRemain <= 11; soccerRemain++) {
                for (int baseballRemain = 0; baseballRemain <= 9; baseballRemain++) {
                    long previous = dp[i - 1][soccerRemain][baseballRemain];

                    if (previous == Long.MIN_VALUE) {
                        continue;
                    }

                    dp[i][soccerRemain][baseballRemain] =
                            Math.max(
                                    dp[i][soccerRemain][baseballRemain],
                                    previous
                            );

                    if (soccerRemain > 0) {
                        dp[i][soccerRemain - 1][baseballRemain] =
                                Math.max(
                                        dp[i][soccerRemain - 1][baseballRemain],
                                        previous + soccer
                                );
                    }

                    if (baseballRemain > 0) {
                        dp[i][soccerRemain][baseballRemain - 1] =
                                Math.max(
                                        dp[i][soccerRemain][baseballRemain - 1],
                                        previous + baseball
                                );
                    }
                }
            }
        }

        System.out.print(dp[N][0][0]);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}