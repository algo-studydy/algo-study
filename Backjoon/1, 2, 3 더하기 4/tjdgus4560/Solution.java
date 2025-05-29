import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        int[] dp = new int[10001];
        dp[0] = 1;

        // 숫자 1을 사용하는 조합
        for(int i=1; i<=10000; i++){
            dp[i] += dp[i-1];
        }

        // 숫자 2를 사용하는 조합
        for(int i=2; i<=10000; i++){
            dp[i] += dp[i-2];
        }

        // 숫자 3을 사용하는 조합
        for(int i=3; i<=10000; i++){
            dp[i] += dp[i-3];
        }

        for(int i=0; i<t; i++){
            int n = sc.nextInt();
            System.out.println(dp[n]);
        }
    }
}
