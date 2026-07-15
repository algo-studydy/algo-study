
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int[][] bags;
    static int N, M;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bags = new int[N][2];
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            bags[i][0] = Integer.parseInt(st.nextToken());
            bags[i][1] = Integer.parseInt(st.nextToken());
        }
        dp = new int[M + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;

        for(int i=1;i<=M;i++) {
            for(int j=0;j<N;j++) {
                int w = bags[j][0];
                int v = bags[j][1];
                if(i >= w) {
                    if(dp[i - w] == Integer.MIN_VALUE) continue;

                    dp[i] = Math.max(dp[i], dp[i - w] + v);
                }
            }
        }

        int max = 0;
        for(int i=1;i<=M;i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
