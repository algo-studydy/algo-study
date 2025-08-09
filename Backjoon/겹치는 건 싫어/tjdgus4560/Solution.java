import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static HashMap<Integer, Integer> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for ( int i = 0; i < N; i++ ) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        hm = new HashMap<>();
        int left = 0;
        int right = 0;
        int max = 0;

        while ( right < N ) {
            int num = arr[right];
            hm.put(num, hm.getOrDefault(num, 0) + 1);

            //K개 넘으면 왼쪽 포인터 이동
            while ( hm.get(num) > K ) {
                int leftNum = arr[left];
                hm.put(leftNum, hm.get(leftNum) - 1);
                left++;
            }

            max = Math.max(max, right - left + 1);
            right++;
        }

        System.out.println(max);
    }
}
