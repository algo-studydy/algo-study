import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n]; //LIS 나타내는 배열

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
            dp[i] = 1; //자기자신 길이1
        }

        // 임의의 순서로 줄을 서있을때 LIS(최장 증가 부분 수열)를 구하면 움직이지 않아도 되는 아이들의 수가 나옴
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[j]+1, dp[i]);
                }
            }
        }

        int max =0;
        for(int c : dp){
            max = Math.max(max, c);
        }

        // 움직여야 하는 횟수 = 전체 아이들의 수 - lis
        System.out.println(n-max);
    }
}
