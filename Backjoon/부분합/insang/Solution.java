import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] nums = new int[n];
        int[] prefix = new int[n+1];
        int answer = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
            prefix[i+1] = prefix[i] + nums[i];  // 누적합 배열 갱신
        }

        System.out.println(Arrays.toString(prefix));

        // 두 개의 포인터 선언
        int left = 1;
        int right = 1;
        while(left <= right && right <= n){
            int sum = prefix[right] - prefix[left-1];

            // 합이 s 미만이면 right 값을 증가 시킴
            if(sum < s){
                right++;
            }
            // 합이 s 이상이면 left 값을 증가 시켜 구간의 길이를 단축 시킴
            else{
                answer = Math.min(answer, right - (left-1));
                left++;
            }
        }

        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
