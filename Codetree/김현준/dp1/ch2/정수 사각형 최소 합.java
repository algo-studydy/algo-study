package codetree.시뮬레이션.격자안에서터지고떨어지는경우;


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
        dp[0][N-1] = map[0][N-1];
        // 왼쪽
        for(int j=N-2;j>=0;j--){
            dp[0][j] = dp[0][j+1] + map[0][j];
        }
        // 아래
        for(int i=1;i<N;i++) {
            dp[i][N-1] = dp[i-1][N-1] + map[i][N-1];
        }
        // 탐색
        for(int i=1;i<N;i++) {
            for(int j=N-2;j>=0;j--) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j+1]) + map[i][j];
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(dp[N-1][0]);
    }


}
