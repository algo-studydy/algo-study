import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static int INF = 100_000;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.print(go());
    }

    static int go() {
        Deque<Integer> dQ = new ArrayDeque<>();
        dQ.add(N);
        int[] dist = new int[100_001];
        Arrays.fill(dist, INF + 1);
        dist[N] = 0;

        while (!dQ.isEmpty()) {
            Integer n = dQ.poll();

            if (n == K) {
                return dist[n];
            }

            int mulTwo = n * 2;
            int minusOne = n - 1;
            int plusOne = n + 1;
            if (mulTwo <= INF && dist[mulTwo] > dist[n]) {
                dist[mulTwo] = dist[n];
                dQ.addFirst(mulTwo);
            }
            if (minusOne >= 0 && dist[minusOne] > dist[n] + 1) {
                dist[minusOne] = dist[n] + 1;
                dQ.add(minusOne);
            }
            if (plusOne <= INF && dist[plusOne] > dist[n] + 1) {
                dist[plusOne] = dist[n] + 1;
                dQ.add(plusOne);
            }
        }

        return -1;
    }
}
