import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ct0704 {
    static int N;
    static int[]jumpCount;
    static int position, count;
    static int ans;

    static void go(int idx) {
        if (idx >= N) {
            return;
        }

        for (int diff=1;diff<=jumpCount[idx];diff++) {
            position += diff;
            count += 1;
            if (position >= N-1) ans = Math.min(ans, count);
            go(idx+diff);
            count -= 1;
            position -= diff;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        jumpCount = new int[N];
        ans = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            jumpCount[i] = Integer.parseInt(st.nextToken());
        }

        go(0);

        System.out.print(ans==Integer.MAX_VALUE ? -1 : ans);
    }
}
