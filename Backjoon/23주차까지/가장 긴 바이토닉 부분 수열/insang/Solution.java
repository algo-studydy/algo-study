import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] front = new int[n];  // 앞에서부터 증가하는 부분 수열 길이
        int[] back = new int[n];  // 뒤에서부터 증가하는 부분 수열 길이
        int answer = 0;

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
            front[i] = 1;
            back[i] = 1;
        }

        for(int i = 0; i < n; i++){
            // 앞에서부터 증가하는 부분수열 길이 기록
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    front[i] = Math.max(front[i], front[j]+1);
                }
            }

            // 뒤에서부터 증가하는 부분수열 길이 기록
            for(int k = n-1; k > n-1-i; k--){
                if(nums[k] < nums[n-1-i]) back[n-1-i] = Math.max(back[n-1-i], back[k] + 1);
            }
        }

        for (int i = 0; i < n; i++){
            answer = Math.max(answer, front[i] + back[i] - 1);
        }

        System.out.println(answer);
    }
}
