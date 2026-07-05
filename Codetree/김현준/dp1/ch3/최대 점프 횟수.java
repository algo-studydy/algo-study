
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N;
    static int[] arr, dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for(int i=1;i<N;i++) {
            for(int j=0;j<i;j++) {
                if(dp[j] == -1) continue;
                if(j + arr[j] >= i) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for(int i=0;i<N;i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);


    }
}
