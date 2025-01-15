import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        //dp[i] : 금액i 를 만드는 경우의 수
        int[] dp = new int[k+1];
        int[] coins = new int[n];

        for(int i=0; i<n; i++){
            coins[i] = sc.nextInt();
        }

        // 0원을 만드는 경우는 동전을 안쓰는 1가지
        dp[0] = 1;
        for(int i=0; i<n; i++){
            int val = coins[i];
            for(int j=val; j<=k; j++){
                dp[j] = dp[j]+dp[j-val];
            }
        }
        System.out.println(dp[k]);
    }
}
