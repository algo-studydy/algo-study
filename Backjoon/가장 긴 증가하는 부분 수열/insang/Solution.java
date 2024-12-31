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
            dp[i] = 1;  // dp 배열을 최솟값인 1로 초기화
        }

        for(int i = 0; i < n; i++){  // 타겟 숫자
            for(int j = 0; j < i; j++){  // 첫 번째 숫자부터 타겟 까지 비교
                if(nums[j] < nums[i]) {  // 현재 숫자가 타겟보다 작으면 갱신
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    answer = Math.max(answer, dp[i]);
                }
            }
        }

        System.out.println(answer);
    }
}
