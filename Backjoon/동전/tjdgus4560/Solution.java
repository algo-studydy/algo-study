import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-->0){
            int N = sc.nextInt();
            int[] coin = new int[N+1];

            for(int i=0; i<N; i++){
                coin[i] = sc.nextInt(); //동전단위;
            }
            int M = sc.nextInt(); //만들어야 하는 금액
            int[] dp = new int[M+1]; // dp[i] : i금액 만들 수 있는 경우의 수

            dp[0] = 1;

            for(int i=0; i<N; i++){
                for(int j=coin[i]; j<=M; j++){
                    dp[j] = dp[j] + dp[j-coin[i]];
                }
            }
            System.out.println(dp[M]);
        }
    }
}
