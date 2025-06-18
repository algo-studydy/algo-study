import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] dp = new int[k+1][n+1]; //dp[k][n] : k개의 수로 n을 만드는 경우

        // dp[1][n] : 1개의 수로 n을 만드는건 한가지
        for(int i=0; i<=n; i++){
            dp[1][i] = 1;
        }

        // dp[k][0] : k개의 수로 0을 만드는건 0을 k개 더하는 한가지
        for(int i=1; i<=k; i++){
            dp[i][0] = 1;
        }

        for(int i=2; i<=k; i++){
            for(int j=1; j<=n; j++){
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1_000_000_000; // dp[i-1][j] : 마지막수를 0 으로 가정할때 이전까지의 값, dp[i][j-1] : 마지막수가 1이상일떄의 값들의 누적
            }
        }

        System.out.println(dp[k][n]);
    }
}
