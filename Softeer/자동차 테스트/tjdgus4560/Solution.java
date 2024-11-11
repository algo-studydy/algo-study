import java.io.*;
import java.util.*;

public class Main {
    public static long solution(long[] arr, long mi) {
        int n = arr.length;
        long count = 0;

        int keyIdx = binarySearch(arr, mi, 0, n-1);
        while(keyIdx < n && keyIdx != -1 && arr[keyIdx] == mi) {
            int left = keyIdx;
            int right = n - (keyIdx + 1);
            // 가능한 조합 수 추가
            count += left * right;
            keyIdx++;
        }

        return count;
    }

    public static int binarySearch(long[] arr, long key, int low, int high) {
        int mid;

        if(low <= high) {
            mid = (low + high) / 2;

            if(key == arr[mid]) { // 탐색 성공
                return mid;
            } else if(key < arr[mid]) {
                return binarySearch(arr, key ,low, mid-1);
            } else {
                return binarySearch(arr, key, mid+1, high);
            }
        }

        return -1; // 탐색 실패
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextLong();
        }

        // 연비 배열 정렬
        Arrays.sort(arr);

        //중앙값이 mi가 되는 경우의 수 출력
        for (int i = 0; i < q; i++) {
            long mi = sc.nextLong();
            System.out.println(solution(arr, mi));
        }
    }
}