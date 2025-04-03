import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N;
    static int C;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        C = sc.nextInt();

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int ans = binarySearch(arr);
        System.out.println(ans);
    }

    private static int binarySearch(int[] arr) {
        //설치할 집 사이의 거리
        int left = 1;
        int right = arr[N-1] - arr[0];

        while(left <= right){
            int mid = (left+right)/2;

            if(isPossible(arr, mid)){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return right;
    }

    // mid 거리로 C개의 공유기 설치 가능한지 체크
    private static boolean isPossible(int[] arr, int mid) {
        int count = 1;
        int last = arr[0];

        for(int i=1; i<N; i++){
            if(arr[i] - last >= mid){
                count++;
                last = arr[i];
            }
            if(count >= C){
                return true;
            }
        }

        return false;
    }
}
