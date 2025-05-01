import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while(T-- > 0) {
            int n = sc.nextInt();

            int[] sum = new int[n+1];
            int[][] dp = new int[n+1][n+1];

            sum[1] = sc.nextInt();
            for (int i = 2; i <= n; i++) {
                sum[i] = sum[i-1]+sc.nextInt();
            }

            for(int k=1; k<n; k++){
                for(int start=1; start+k<=n; start++){
                    int end = start+k;
                    dp[start][end] = Integer.MAX_VALUE;

                    for(int mid=start; mid<end; mid++){
                        dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid+1][end] + sum[end] - sum[start-1]);
                    }
                }
            }

            System.out.println(dp[1][n]);
        }

    }




}
