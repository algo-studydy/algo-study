import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < i+1; j++){
                arr[i][j] = sc.nextInt();
                dp[i][j] = arr[i][j];
            }
        }

        for(int i = n-1; i > 0; i--){
            for(int j = 0; j <= i; j++){
                // 첫 번째 요소
                if(j == 0){
                    dp[i-1][j] = Math.max(dp[i-1][j], dp[i][j] + arr[i-1][j]);
                }
                // 마지막 요소
                else if(j == i){
                    dp[i-1][j-1] = Math.max(dp[i-1][j-1], dp[i][j] + arr[i-1][j-1]);
                }
                else {
                    dp[i-1][j-1] = Math.max(dp[i-1][j-1], dp[i][j] + arr[i-1][j-1]);
                    dp[i-1][j] = Math.max(dp[i-1][j], dp[i][j] + arr[i-1][j]);
                }
            }

        }
        System.out.println(dp[0][0]);
    }
}
