import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 0;
        int sum = 0, ans = Integer.MAX_VALUE;
        while(right < N) {
            if(sum + arr[right] < S) {
                sum += arr[right];
                right++;
            }
            else {
                ans = Math.min(ans, right - left);
                sum -= arr[left];
                left++;
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans + 1);
    }
}
