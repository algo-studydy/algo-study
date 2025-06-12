import java.util.Arrays;
import java.util.Scanner;

public class BOJ2293동전1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 동전의 종류
        int k = sc.nextInt();  // 가치의 합
        int[] dp = new int[k+1];
        dp[0] = 1;  // 0을 만드는 방법은 아무 동전도 사용하지 않는 1가지 뿐

        for(int i = 0; i < n; i++){
            int coin = sc.nextInt();  // 현재 동전의 가치
            // 현재 동전의 가치로 표현 가능한 1~k원 까지의 경우의 수
            for(int j = 1; j <= k; j++){
                // dp[j−coin] : 이전에 coin을 사용하지 않고 j-coin까지 표현 가능한 수
                if(j - coin >= 0) dp[j] += dp[j - coin];
            }
        }
    }
}
