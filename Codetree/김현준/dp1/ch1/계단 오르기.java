
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] memo;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        if(N <= 3) {
            System.out.println(1); return;
        }
        dp[2] = 1; dp[3] = 1;
        for(int i=4;i<=N;i++) {
            dp[i] = (dp[i-2] + dp[i-3]) % 10007;
        }

        System.out.println(dp[N]);
    }


}
