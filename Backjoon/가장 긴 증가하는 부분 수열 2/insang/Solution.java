import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        // LIS 계산을 위한 ArrayList
        ArrayList<Integer> lis = new ArrayList<>();

        for (int num : nums) {
            // LIS 배열에서 num이 들어갈 위치 찾기 (lower bound)
            int pos = Collections.binarySearch(lis, num);
            if (pos < 0) {
                pos = -(pos + 1); // 삽입 위치 계산
            }

            // 위치에 따라 추가 또는 갱신
            if (pos == lis.size()) {
                lis.add(num); // 배열 끝에 추가
            } else {
                lis.set(pos, num); // 값 갱신
            }
        }

        System.out.println(lis.size());
    }
}
