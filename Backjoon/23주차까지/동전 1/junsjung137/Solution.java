import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        final int MAX = k;
        int[] arr = new int[n];
        int[] dp = new int[MAX + 1];

        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());

            int coin = arr[i];
            for (int j = coin; j <= MAX; j++) {
                dp[j] += dp[j - coin];
            }
        }

        System.out.println(dp[MAX]);
    }
}
