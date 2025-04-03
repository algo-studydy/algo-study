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
        int[] dp2 = new int[A];
        int ans = 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
            dp2[i] = 1;
        }

        for (int i = 1; i < A; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Integer.max(dp[i], dp[j] + 1);
                }
            }
        }
        for (int i = A - 2; i >= 0; i--) {
            for (int j = i + 1; j < A; j++) {
                if (arr[j] < arr[i]) {
                    dp2[i] = Integer.max(dp2[i], dp2[j] + 1);
                }
            }
            ans = Integer.max(ans, dp[i] + dp2[i] - 1);
        }
        System.out.print(ans);
    }
}
