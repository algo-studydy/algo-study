import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        List<Integer> wList = new ArrayList<>();
        int maxMass = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int mass = Integer.parseInt(st.nextToken());
            wList.add(mass);

            maxMass += mass;
        }

        boolean[] dp = new boolean[maxMass + 1];
        dp[0] = true;
        for (int i = 0; i < n; i++) {
            int mass = wList.get(i);

            for (int j = maxMass; j >= mass; j--) {
                dp[j] |= dp[j - mass];
            }
        }

        for (int i = 0; i < n; i++) {
            int mass = wList.get(i);

            for (int j = mass + 1; j <= maxMass; j++) {
                dp[j - mass] |= dp[j];
            }
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int bMass = Integer.parseInt(st.nextToken());   // 공의 무게
            if (bMass <= maxMass && dp[bMass]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }

        System.out.println(sb);
    }
}
