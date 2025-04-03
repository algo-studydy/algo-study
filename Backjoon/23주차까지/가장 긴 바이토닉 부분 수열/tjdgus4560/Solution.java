import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp1 = new int[n]; // 가장긴증가수열
        int[] dp2 = new int[n]; // 가장긴감소수열
        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
            dp1[i] = 1;
            dp2[i] = 1;
        }

        // 가장 긴증가하는 부분수열
        for(int i = 1; i < n; i++){
            for(int j = 0; j < i; j++){
                if(arr[i] > arr[j]){
                    dp1[i] = Math.max(dp1[i], dp1[j] + 1);
                }
            }
        }

        //가장 긴 증가하는 부분수열 역순
        for(int i = n - 2; i >= 0; i--){
            for(int j = n - 1; j > i; j--){
                if(arr[i] > arr[j]){
                    dp2[i] = Math.max(dp2[i], dp2[j] + 1);
                }
            }
        }

        int answer = 0;
        for(int i = 0; i < n; i++){
            answer = Math.max(answer, dp1[i] + dp2[i] - 1);
        }

        System.out.println(answer);
    }
}
