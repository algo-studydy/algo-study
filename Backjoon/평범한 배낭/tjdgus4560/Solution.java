import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //물품의 수
        int K = sc.nextInt(); //버틸수 있는 무게

        int[][] dp = new int[N+1][K+1]; //dp[i][j] : i번째 물건까지 고려했을때 j무게의 가방에서 가질 수 있는 최대 가치
        for(int i=1; i<=N; i++){
            int w = sc.nextInt(); //무게
            int v = sc.nextInt(); //가치

            for(int j=0; j<=K; j++){
                dp[i][j] = dp[i-1][j];
                if(j>=w){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w] + v);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
