import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int ans = 0;
        int left = 0, right = arr[arr.length - 1];
        while(left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            for(int i=0;i<arr.length;i++) {
                if(arr[i] < mid) sum += arr[i];
                else sum += mid;
            }
            // 합산 값이 M보다 작거나 같으면 최대값을 갱신한다.
            if(sum <= M) {
                ans = Math.max(ans, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
