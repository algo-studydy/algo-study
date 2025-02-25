import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();
        //오름차순 정렬
        Arrays.sort(arr);
        int ans = solution(arr, target);

        System.out.println(ans);

    }

    private static int solution(int[] arr, int target) {
        int left = 0;
        int right = arr.length-1;
        int count=0;
        while(left < right){
            int sum = arr[left] + arr[right];
            if(sum == target){
                count++;
                left++;
                right--;
            }else if(sum < target){
                left++;
            }else{
                right--;
            }
        }
        return count;
    }
}
