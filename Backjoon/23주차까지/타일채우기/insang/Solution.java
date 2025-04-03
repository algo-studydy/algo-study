import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[31];
        dp[0] = 1;  // 타일을 사용하지 않고 가득 채우는 경우
        dp[2] = 3;
        dp[4] = 11;

        // n이 홀수면 가능한 수는 0 이므로 i를 2씩 증가 시킴
        for(int i = 6; i <= n; i+=2){
            dp[i] = 4 * dp[i-2] - dp[i-4];
        }
        System.out.println(dp[n]);
    }
}
