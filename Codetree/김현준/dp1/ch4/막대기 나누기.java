
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int[] arr;
    static int N;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i=1;i<=N;i++) {
            for(int j=0;j<N;j++) {
                if(i >= j) {
                    if(dp[i - j] == -1) continue;

                    dp[i] = Math.max(dp[i], dp[i - j] + arr[j]);
                }
            }
        }

        System.out.println(dp[N]);

    }
}
