import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); //동전의 갯수
        int k = sc.nextInt(); //원하는 가치
        int[] dp = new int[k+1];
        int[] coin = new int[n];

        for(int i=0; i<n; i++){
            coin[i] = sc.nextInt();
        }

        dp[0] = 1;
        for(int i=0; i<n; i++){
            int c = coin[i];
            for(int j=c; j<k+1; j++){
                dp[j] = dp[j] + dp[j-c];
            }
        }

        System.out.println(dp[k]);
    }
}
