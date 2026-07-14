
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dp;
    static int N, M;
    static int[] arr = {1, 2, 5};
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];

        dp[0] = 1;
        for(int i=1;i<=N;i++) {
            for(int j=0;j<3;j++) {
                if(i >= arr[j]) {
                    dp[i] = (dp[i] + dp[i-arr[j]]) % 10007;
                }
            }
        }

        System.out.println(dp[N]);
    }
}
