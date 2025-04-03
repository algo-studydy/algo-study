import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] dp = new int[n];
        int answer = 1;

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
            dp[i] = 1;
        }

        for(int i = 0; i < n; i++){
            // 뒤에서부터 증가하는 부분수열의 길이 기록
            for(int j = n-1; j > n-1-i; j--){
                if(nums[j] < nums[n-1-i]) {
                    dp[n-1-i] = Math.max(dp[n-1-i], dp[j] + 1);
                    answer = Math.max(answer, dp[n-1-i]);
                }
            }
        }

        System.out.println(answer);
    }
}
