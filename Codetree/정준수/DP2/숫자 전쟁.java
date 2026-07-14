import java.io.*;
import java.util.*;

public class ct1703 {
    static int N;
    static int[] firstArr, secondArr;
    static int[][] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        firstArr = new int[N];
        secondArr = new int[N];
        dp = new int[N+1][N+1];

        for (int[] dpt: dp) Arrays.fill(dpt, Integer.MIN_VALUE);

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            firstArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            secondArr[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void go() {
        dp[0][0] = 0;
        for (int r=0;r<N;r++) {
            int secondNum = secondArr[r];

            for (int c=0;c<N;c++) {
                if (dp[r][c] == Integer.MIN_VALUE) continue;

                int firstNum = firstArr[c];

                if (secondNum < firstNum) {
                    dp[r+1][c] = Math.max(dp[r+1][c], dp[r][c] + secondNum);
                } else if (secondNum > firstNum) {
                    dp[r][c+1] = Math.max(dp[r][c+1], dp[r][c]);
                }

                dp[r+1][c+1] = Math.max(dp[r+1][c+1], dp[r][c]);
            }
        }

        int max = 0;
        for (int i=0;i<=N;i++) {
            max = Math.max(max, dp[N][i]);
        }

        System.out.print(max);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}

