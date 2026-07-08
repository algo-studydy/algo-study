import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct1504 {
    static int N, M;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for (int r=0;r<N;r++) {
            st = new StringTokenizer(br.readLine());
            for (int c=0;c<M;c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int c=0;c<M;c++) dp[N-1][c] = 1;
        for (int r=0;r<N;r++) dp[r][M-1] = 1;

        for (int r=N-2;r>=0;r--) {
            for (int c=M-2;c>=0;c--) {

                for (int i=r+1;i<N;i++) {
                    for (int j=c+1;j<M;j++) {
                        if (map[r][c] >= map[i][j]) continue;

                        dp[r][c] = Math.max(dp[r][c], dp[i][j] + 1);
                    }
                }

            }
        }

        System.out.print(dp[0][0]);
    }
}
