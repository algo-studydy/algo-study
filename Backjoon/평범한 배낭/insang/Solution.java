import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 물품의 수
        int k = sc.nextInt();  // 무게 제한

        int[] w = new int[n+1];  // 무게
        int[] v = new int[n+1];  // 가치
        int[][] dp = new int[n+1][k+1];

        for(int i = 1; i <= n; i++){
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k; j++){
                // i번째 무게를 더 담을 수 없는 경우
                if(w[i] > j){
                    dp[i][j] = dp[i-1][j];
                }

                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]] + v[i]);
                }
            }
        }

        System.out.println(dp[n][k]);
    }
}
