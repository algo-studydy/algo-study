import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        int[] arr = new int[n+1];
        int answer = Integer.MIN_VALUE;

        // 누적합 배열 갱신
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
            arr[i+1] = arr[i] + nums[i];
        }
//        System.out.println(Arrays.toString(arr));

        for(int i = k; i <= n; i++){
            answer = Math.max(answer, arr[i] - arr[i-k]);
        }

        System.out.println(answer);
    }
}
