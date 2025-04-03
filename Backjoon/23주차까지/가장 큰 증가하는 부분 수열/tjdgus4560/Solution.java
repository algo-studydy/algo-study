import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            dp[i] = arr[i];
        }

        for(int i = 1; i < n; i++){
            int tmp = 0;
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    tmp = Math.max(tmp, dp[j]);
                }
            }
            dp[i] = tmp + dp[i];
        }

        int ans = 0;
        for(int i : dp){
            ans = Math.max(ans, i);
        }
        System.out.println(ans);
    }
}
