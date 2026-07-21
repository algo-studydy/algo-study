import java.io.*;
import java.util.*;

public class ct1806 {
    static int N, M;
    static int[] arr;
    static int[][][] dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new int[N+1][4+1][M+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
    }

    static void go() {
        int ans = 0;
        for (int i=1;i<=N;i++) {
            for (int num=1;num<=4;num++) {
                int val = (num == arr[i] ? 1 : 0);

                for (int num2=1;num2<=4;num2++) {
                    boolean isSame = (num==num2);

                    for (int sim=0;sim<=M;sim++) {
                        if (isSame) dp[i][num][sim] = Math.max(dp[i][num][sim], dp[i-1][num][sim] + val);
                        else if (sim+1 <= M) dp[i][num][sim] = Math.max(dp[i][num][sim], dp[i-1][num2][sim+1] + val);

                        ans = Math.max(ans, dp[i][num][sim]);
                    }
                }
            }
        }

        System.out.print(ans);
    }

    public static void main(String[] args) throws Exception {
        init();

        go();
    }
}
