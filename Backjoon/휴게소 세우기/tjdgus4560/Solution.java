import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N; // 현재 휴게소 갯수
    static int M; // 추가할 휴게소 갯수
    static int L; // 고속도로 길이

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        L = sc.nextInt();

        int[] arr = new int[N + 2];

        for(int i=1; i<=N; i++){
            arr[i] = sc.nextInt();
        }

        arr[0] = 0; // 시작점 추가
        arr[N+1] = L; // 끝점 추가
        Arrays.sort(arr);

        int left = 1;
        int right = L;
        int ans = 0;

        while(left <= right){
            int mid = (left + right) / 2; // 현재 최대 허용 간격 후보
            int count = 0;

            // 모든 구간에 대해 mid 이하로 쪼갤때 필요한 휴게소 수 카운트
            for(int i=1; i<arr.length; i++){
                int dist = arr[i] - arr[i-1];
                count += (dist - 1) / mid;
            }

            if(count > M){
                // 필요 수가 M보다 많으니까 더 큰값 필요
                left = mid + 1;
            } else {
                ans = mid;
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
