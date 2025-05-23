import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] cost = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<3; j++){
                cost[i][j] = sc.nextInt();
            }
        }

        int ans = Integer.MAX_VALUE;

        for(int k=0; k<3; k++){ //첫 집을 r,g,b중 뭐로 할지 정하는 변수 k
            for(int i=0; i<3; i++){ //첫 집을 제외한 집을 가능한 최대값으로 설정
                if(i==k){
                    dp[0][i] = cost[0][i];
                }else{
                    dp[0][i] = 1000 * 1000;
                }
            }

            for(int i=1; i<n; i++){
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
            }

            for(int i=0; i<3; i++){ //1번째 집과 동일하지 않은 색깔일때만 최소값 업데이트
                if(i!=k) ans = Math.min(ans, dp[n-1][i]);
            }
        }

        System.out.println(ans);
    }
}
