import java.util.Scanner;

public class Main {
    static int cnt = 0;
    static final int mod = 1_000_000_007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        fibonacci(n);
        System.out.println(cnt + " " + (n-2));
    }

    private static void fibonacci(int n) {
        int[] dp = new int[n+1];
        dp[1] = dp[2] = 1;

        for(int i = 3; i <= n; i++){
            // 한번의 반복마다 모듈러 연산을 통해 항상 작은 값을 보장
            // 모듈러 연산을 for문 밖에서 한번만 할 경우 오답
            dp[i] = (dp[i-1] + dp[i-2]) % mod;
        }
        cnt = dp[n];
    }
}
