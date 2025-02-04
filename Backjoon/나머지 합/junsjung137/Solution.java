import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] arr = new long[N+1];
        long[] cnt = new long[M];

        cnt[0] = 1;
        long answer = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = (arr[i - 1] + Long.parseLong(st.nextToken())) % M;
        }
        for (int i = 1; i <= N; i++) {
            answer += cnt[(int)arr[i]];
            cnt[(int)arr[i]]++;
        }
        System.out.println(answer);
    }
}