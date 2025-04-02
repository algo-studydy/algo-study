import java.util.*;

public class Main {
    static int[] arr;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int count=0;
        for(int i=0; i<n; i++){
            if(search(i)) count++;
        }

        System.out.println(count);
    }

    private static boolean search(int t) {
        int target =arr[t];
        int left = 0;
        int right = n-1;
        while(left<right){
            if (left == t) {
                left++;
                continue;
            }
            if (right == t) {
                right--;
                continue;
            } //자기자신 건너뛰기


            int sum = arr[left]+arr[right];
            if(sum == target){
                return true;
            }else if(sum > target){
                right--;
            }else{
                left++;
            }

        }
        return false;
    }
}
