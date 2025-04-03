import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        int[] dp = new int[n];

        for(int i=0; i<n; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            dp[i] = 1;
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[i][1] > arr[j][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 0;
        for(int i=0; i<n; i++){
            max = Math.max(dp[i], max);
        }
        System.out.println(n-max);
    }
}
