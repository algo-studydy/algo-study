
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] dp;
    static int[] arr;
    static int N;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int total = 0;
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }

        Arrays.sort(arr);
        dp = new boolean[total + 1];
        dp[0] = true;

        // 한 개씩만 골라서 가능한 모든 수의 합을 저장한다. 그 중에, total - i 와 i 의 차이가 최소인 값을 찾는다.
        for(int i=0;i<N;i++) {
            int now = arr[i];
            for(int j=dp.length-1;j>=now;j--) {
                if(dp[j - now]) {
                    dp[j] = true;
                }
            }
        }

        int diff = Integer.MAX_VALUE;

        for(int i=1;i<=total;i++) {
            if(dp[i]) {
                int ch = total - i;
                diff = Math.min(diff, Math.abs(i - ch));
            }
        }

        System.out.println(diff);

    }
}
