import java.util.*;

public class BOJ17404RGB거리2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] cost = new int[n][3];
        for (int i = 0; i < n; i++) {
            cost[i][0] = sc.nextInt(); // R
            cost[i][1] = sc.nextInt(); // G
            cost[i][2] = sc.nextInt(); // B
        }

        int result = Integer.MAX_VALUE;

        // 첫 번째 집의 색을 0(R), 1(G), 2(B)로 고정하고 세 번 반복
        for (int first = 0; first < 3; first++) {
            int[][] dp = new int[n][3];

            // 첫 번째 집 색 고정
            for (int i = 0; i < 3; i++) {
                if (i == first) dp[0][i] = cost[0][i];
                else dp[0][i] = 1_000_000; // 선택 불가능한 색은 큰 값으로 막아둠
            }

            // DP 갱신
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + cost[i][2];
            }

            // 마지막 집의 색은 첫 집과 같으면 안 됨
            for (int lastColor = 0; lastColor < 3; lastColor++) {
                if (lastColor == first) continue;
                result = Math.min(result, dp[n - 1][lastColor]);
            }
        }

        System.out.println(result);
    }
}
