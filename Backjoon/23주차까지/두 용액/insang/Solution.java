import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }

        Arrays.sort(nums);

        int[] answer = new int[2];
        int target = Integer.MAX_VALUE;
        int left = 0;
        int right = n-1;

        while(left < right){
            int sum = nums[left] + nums[right];  // 두 수의 합
            int AbsSum = Math.abs(nums[left] + nums[right]);  // 두 수의 합 절댓값

            // 절댓값이 기존의 수 보다 작으면 정답 갱신 (0에 가까운 수)
            if(AbsSum < target){
                target = AbsSum;
                answer[0] = nums[left];
                answer[1] = nums[right];
            }
            // 두 수의 합이 0이면 반복문 종료
            if(sum == 0) break;
                // 두 수의 합이 음수면 값을 크게 만들어야 함(0에 가깝게 하기 위해)
            else if(sum < 0) left++;
                // 두 수의 합이 양수면 값을 작게 만들어야 함
            else right--;

        }
        System.out.println(answer[0] + " " + answer[1]);

    }
}
