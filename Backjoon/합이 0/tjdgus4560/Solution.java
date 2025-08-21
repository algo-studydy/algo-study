import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];

        for (int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        long answer = 0;

        for (int i=0; i<N-2; i++) {
            for (int j=i+1; j<N-1; j++) {
                int sum = arr[i] + arr[j];
                int target = -sum;

                int left = lowerBound(arr, j + 1, N, target);
                int right = upperBound(arr, j + 1, N, target);

                answer += (right - left);
            }
        }

        System.out.println(answer);
    }

    // target 이상 처음 등장하는 위치 반환
    static int lowerBound(int[] arr, int left, int right, int target) {
        while (left<right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // target 초과 처음 등장하는 위치 반환
    static int upperBound(int[] arr, int left, int right, int target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
