
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int N, M;
    static int[][] bags;
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

        dp = new int[M+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i=0;i<N;i++) {
            int w = bags[i][0];
            int v = bags[i][1];
            for(int j=M;j>=w;j--) {
                if(dp[j-w] == -1) continue;
                dp[j] = Math.max(dp[j], dp[j-w] + v);
            }
        }

        int max = 0;
        for(int i=0;i<=M;i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
