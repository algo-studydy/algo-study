import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[301];
        int[] dp = new int[301];

        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }

        // n번째 계단까지 가기 위한 방법
        // 1. n-3번째 계단을 밟고, n-1번째 계단을 밟은 뒤 도달
        // 2. n-2번째 계단을 밟은 뒤 도달

        dp[1] = nums[1];
        dp[2] = nums[1] + nums[2];

        for(int i = 3; i <= n; i++){
            dp[i] = Math.max(dp[i-3] + nums[i-1], dp[i-2]) + nums[i];
        }

        System.out.println(dp[n]);
    }
}
