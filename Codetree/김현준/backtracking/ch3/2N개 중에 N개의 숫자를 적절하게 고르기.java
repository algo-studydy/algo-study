
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int sum;
    static boolean[] v;

    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N*2];
        sum = 0;
        for (int i = 0; i < N * 2; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }

        choose(0, 0, 0);
        System.out.println(min);
    }

    private static void choose(int a, int s, int idx) {
        // basis
        if (a == N) {
            int remain = sum - s;
            min = Math.min(min, Math.abs(remain - s));
            return;
        }

        if (a > N) {
            return;
        }

        // inductive
        for (int i = idx; i < 2 * N; i++) {
            choose(a + 1, s + arr[i], i + 1);
        }
    }

    // 2개의 집합으로 나눠서 생각할때 함수에서 a, b로 갯수를 생각하면서 가져가면?
}
