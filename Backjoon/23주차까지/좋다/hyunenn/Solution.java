
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 좋다 {
    static int N;
    static int[] arr;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int answer = 0;
        for(int idx=0;idx<N;idx++) {
            int target = arr[idx], start = 0, end = N - 1;
            while(start < end) {
                // 현재 위치가 각각 구하는 값이 자기 자리라면 패스
                if(start == idx) {
                    start++;
                    continue;
                }
                if(end == idx) {
                    end--;
                    continue;
                }

                if(arr[start] + arr[end] > target) end--; // target 보다 크면 end 줄이기
                else if(arr[start] + arr[end] < target) start++;
                else {
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
