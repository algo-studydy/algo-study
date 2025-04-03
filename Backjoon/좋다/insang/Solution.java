import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }

        // 투 포인터 알고리즘 사용을 위한 정렬
        Arrays.sort(nums);

        int answer = 0;

        for(int i = 0; i < n; i++){
            int left = 0;
            int right = n-1;
            int target = nums[i];

            // 자기 자신을 포인터로 잡지 않도록 조정
            if(left == i) left = i+1;
            if(right == i) right = i-1;
            while(left < right){
                if(nums[left] + nums[right] == target){
                    answer++;
                    break;
                }
                else if (nums[left] + nums[right] < target) {
                    left++;
                    // left++ 이후, 포인터가 자기 자신을 바라보지 않도록 건너 뜀
                    if(i == left) left++;
                }
                else {
                    right--;
                    // right++ 이후, 포인터가 자기 자신을 바라보지 않도록 건너 뜀
                    if(i == right) right--;
                }
            }
        }
        System.out.println(answer);
    }
}
