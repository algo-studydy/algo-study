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

        int x = sc.nextInt();
        int answer = 0;

        // 투 포인터 알고리즘 사용
        int left = 0;
        int right = n-1;

        while(left < right){
            // 두 개의 포인터를 사용해 sum 갱신
            int sum = nums[left] + nums[right];
            if(sum == x){
                answer++;
                left++;
                right--;
            }
            // 두 포인터의 합이 x 보다 크면 오른쪽 포인터 -1 (더해진 값을 줄임)
            else if(sum > x){
                right--;
            }
            // 두 포인터의 합이 x 보다 작으면 왼쪽 포인터 +1 (더해진 값을 키움)
            else left++;
        }
        System.out.println(answer);
    }
}
