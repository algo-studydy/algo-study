import java.io.*;
import java.util.*;

public class ct1801 {
    static int N, M;
    static int[][] arr, dp;

    static void init() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][3];
        dp = new int[M+1][N+1];

        for (int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[i][0] = s;
            arr[i][1] = e;
            arr[i][2] = v;
        }
    }

    static void go() {
        int ans = 0;

        for (int i=0;i<=M;i++) Arrays.fill(dp[i], -1);

        for (int i=1;i<=N;i++) if (arr[i][0] <= 1 && arr[i][1] >= 1) dp[1][i] = 0;

        for (int day=2;day<=M;day++) {
            for (int i=1;i<=N;i++) {
                if (arr[i][0] > day || arr[i][1] < day) continue;

                for (int j=1;j<=N;j++) {
                    if(dp[day-1][j] == -1) continue;

                    if (dp[day][i] < dp[day-1][j] + Math.abs(arr[j][2] - arr[i][2])) {
                        dp[day][i] = dp[day-1][j] + Math.abs(arr[j][2] - arr[i][2]);
                        if (day==M) ans = Math.max(ans, dp[day][i]);
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
