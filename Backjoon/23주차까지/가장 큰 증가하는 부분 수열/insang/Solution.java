import java.util.Arrays;
import java.util.Scanner;

public class BOJ11055가장큰증가하는부분수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] dp = new int[n];
        int answer = 0;

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
            dp[i] = nums[i];
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] + nums[i], dp[i]);
                }
            }
        }

        for(int num : dp){
            answer = Math.max(answer, num);
        }
        System.out.println(answer);
    }
}
