import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        int[] answer = solution(arr);
        System.out.println(arr[answer[0]] +" "+arr[answer[1]]);
    }

    private static int[] solution(int[] arr) {
        int left = 0;
        int right = arr.length-1;
        int min = Integer.MAX_VALUE;
        int[] ans = new int[2];

        //모든 값이 양수일경우
        if (arr[0] > 0) {
            return new int[]{0, 1};
        }

        // 모든 값이 음수일 경우
        if (arr[n - 1] < 0) {
            return new int[]{n-2, n-1};
        }

        while(left<right){
            int sum = arr[left] + arr[right];
            if(Math.abs(sum) < min){
                ans[0] = left;
                ans[1] = right;
                min = Math.abs(sum);
            }
            if(sum == 0){
                return new int[]{left, right};
            }else if(sum > 0){
                right--;
            }else{
                left++;
            }
        }
        return ans;
    }
}
