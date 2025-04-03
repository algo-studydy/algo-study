import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        String s1 = br.readLine();
        String s2 = br.readLine();
        int s1Length = s1.length();
        int s2Length = s2.length();
        int[][] dp = new int[2][s2Length + 1];
        int p = 0;
        for (int i = 1; i <= s1Length; i++) {
            for (int j = 1; j <= s2Length; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[p % 2][j] = dp[(p + 1) % 2][j - 1] + 1;
                } else {
                    dp[p % 2][j] = Math.max(dp[(p + 1) % 2][j], dp[p % 2][j - 1]);
                }
            }
            p++;
        }
        System.out.println(dp[(p + 1) % 2][s2Length]);
    }
}
