import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] sum = new long[N+1]; // 누적합 배열
        long[] modCount = new long[M]; // 나머지 카운트 배열

        // 첫 번째 원소
        sum[0] = 0;
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Long.parseLong(st.nextToken());
        }

        // 누적합 % M 값을 카운트
        long answer = 0;
        for (int i = 1; i <= N; i++) {
            long mod = sum[i] % M;
            if (mod == 0) {
                answer++;
            }
            modCount[(int) mod]++;
        }

        // 같은 나머지를 가진 누적합 쌍들의 조합 개수
        for (int i = 0; i < M; i++) {
            if (modCount[i] >= 2) {
                answer += (modCount[i] * (modCount[i] - 1)) / 2;
            }
        }

        System.out.println(answer);
    }
}
