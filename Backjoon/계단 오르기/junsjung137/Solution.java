import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 3];
        int[] dp = new int[n + 3];

        for (int i = 2; i <= n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        dp[1] = 0;
        dp[2] = arr[2];
        for (int i = 3; i <= n + 2; i++) {
            int a = dp[i - 2] + arr[i];
            int b = dp[i - 3] + arr[i - 1] + arr[i];

            dp[i] = Math.max(a, b);
        }

        System.out.println(dp[n + 1]);
    }
}
