import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ct1707 {
    static final int DIV = 1_000_000_007;

    static int N;
    static long[][] dp;

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in)
        );

        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1][9];
    }

    static void go() {
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int t = 0; t <= 2; t++) {
                for (int b = 0; b <= 2; b++) {
                    int currentState = t * 3 + b;
                    long count = dp[i - 1][currentState];

                    if (count == 0) {
                        continue;
                    }

                    int nextState = t * 3;
                    dp[i][nextState] =
                            (dp[i][nextState] + count) % DIV;

                    if (b < 2) {
                        nextState = t * 3 + (b + 1);
                        dp[i][nextState] =
                                (dp[i][nextState] + count) % DIV;
                    }

                    if (t < 2) {
                        nextState = (t + 1) * 3;
                        dp[i][nextState] =
                                (dp[i][nextState] + count) % DIV;
                    }
                }
            }
        }

        long answer = 0;

        for (int state = 0; state < 9; state++) {
            answer = (answer + dp[N][state]) % DIV;
        }

        System.out.print(answer);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}