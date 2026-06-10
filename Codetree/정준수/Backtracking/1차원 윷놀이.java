import java.io.*;
import java.util.*;

public class ct0702 {
    static int N, M, K;
    static int[] moveCount;
    static int[] position;
    static boolean[] visited;
    static int arrived;
    static int ans;

    static void go(int turn) {
        if (turn == N) {
            ans = Math.max(ans, arrived);
            return;
        }

        for (int k=0;k<K;k++) {
            int move = moveCount[turn];
            boolean isArrived = (position[k] + move) / (M-1) >= 1;

            int diff = (isArrived && !visited[k]) ? 1 : 0;
            visited[k] = isArrived;
            position[k] += move;
            arrived += diff;
            go(turn + 1);
            arrived -= diff;
            position[k] -= move;
            visited[k] = position[k] / (M-1) >= 1;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        moveCount = new int[N];
        position = new int[K];
        visited = new boolean[K];
        ans = 0;
        arrived = 0;

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            moveCount[i] = Integer.parseInt(st.nextToken());
        }

        go(0);

        System.out.print(ans);
    }
}
