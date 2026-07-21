
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
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[M + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i=0;i<N;i++) {
            int num = arr[i];
            for(int j=M;j>=num;j--) {
                if(dp[j - num] == -1) continue;
                dp[j] = Math.max(dp[j], dp[j - num] + 1);
            }
        }
        System.out.println(dp[M] != -1 ? "Yes" : "No");

    }
}
