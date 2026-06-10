import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0804 {
    static int N, total;
    static int[] map;
    static int sum, ans;

    static void go(int curIdx, int count) {
        if (count == N) {
            ans = Math.min(ans, Math.abs(sum - (total-sum)));
            return;
        }

        for (int idx=curIdx + 1;idx<2*N;idx++) {
            int num = map[idx];
            sum += num;
            go(idx, count + 1);
            sum -= num;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[2*N];

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<2*N;i++) {
            map[i] = Integer.parseInt(st.nextToken());
            ans += map[i];
            total += map[i];
        }

        go(-1, 0);

        System.out.print(ans);
    }
}
