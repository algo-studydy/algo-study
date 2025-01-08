import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] score = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            score[i] = sc.nextInt();
        }
        //dp[n] 은 지금까지 밟아온 최대값
        int[] dp = new int[n + 1];
        dp[1] = score[1];
        if (n > 1) dp[2] = score[1] + score[2];
        if (n > 2) dp[3] = Math.max(score[1] + score[3], score[2] + score[3]);

        // score[i] + dp[i-2] 는 한칸 제끼고 온거
        // score[i] + score[i - 1] + dp[i-3] 는 두칸전에 제끼고온거
        for(int i = 4 ; i <= n ; i++){
            dp[i] = Math.max(score[i] + dp[i-2] , score[i] + score[i - 1] + dp[i-3]);
        }

        System.out.println(dp[n]);
    }
}
