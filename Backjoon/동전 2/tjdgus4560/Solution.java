import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int[] dp = new int[k+1];
        Arrays.fill(dp, 10001);
        dp[0] = 0;

        for(int i=0; i<n; i++){
            for(int j=arr[i]; j<k+1; j++){
                dp[j] = Math.min(dp[j-arr[i]]+1, dp[j]);
            }
        }


        System.out.println(dp[k] == 10001 ? -1 : dp[k]);

    }
}
