import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dp = new int[11];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for(int i = 3 ; i < 11 ; i++){
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        }
        int T = sc.nextInt();
        for(int i = 0 ; i < T; i++){
            int n = sc.nextInt();
            System.out.println(dp[n-1]);
        }
    }
}
