
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int N, M;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp = new int[M+1];

        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i=1;i<=M;i++) {
            for(int j=0;j<N;j++) {
                if(i >= arr[j]) {
                    if(dp[i - arr[j]] == -1) continue;
                    dp[i] = Math.max(dp[i], dp[i - arr[j]] + 1);
                }
            }
        }

        System.out.println(dp[M] == Integer.MAX_VALUE ? -1 : dp[M]);

    }
}
