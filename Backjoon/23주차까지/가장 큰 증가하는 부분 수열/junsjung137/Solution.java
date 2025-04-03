import java.util.*;
import java.io.*;

public class Main {
    static final int MINIMUM = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int[] arr = new int[A];
        int[] dp = new int[A];
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];
        ans = dp[0];
        for (int i = 1; i < A; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Integer.max(dp[i], dp[j]);
                }
            }
            dp[i] += arr[i];
            ans = Integer.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
}
