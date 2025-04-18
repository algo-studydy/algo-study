import java.util.*;

public class BOJ2631줄세우기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] lis = new int[n]; // LIS (가장 긴 증가 부분 수열의 길이 저장)

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            lis[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                }
            }
        }

        int max = 0;  // 가장 긴 증가하는 부분수열 길이
        for (int i = 0; i < n; i++) {
            max = Math.max(max, lis[i]);
        }

        // 전체 인원(n) - 가장 긴 증가하는 부분 수열(max) = 빼야하는 인원 수
        System.out.println(n - max);
    }
}
