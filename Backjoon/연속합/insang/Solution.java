import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] dp = new int[n];
        int answer = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
            dp[i] = nums[i];
        }

        for(int i = 1; i < n; i++){
            if(dp[i] < dp[i-1] + dp[i]){
                dp[i] += dp[i-1];
            }
        }

        for(int i = 0; i < n; i++){
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(answer);
    }
}
