import java.util.Scanner;

public class BOJ15989123더하기4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int max = 10000;  // n의 최대값

        int[] dp = new int[max + 1];
        dp[0] = 1;

        for (int num = 1; num <= 3; num++) {
            for (int i = num; i <= max; i++) {
                dp[i] += dp[i - num];
            }
        }

        for (int i = 0; i < t; i++){
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
