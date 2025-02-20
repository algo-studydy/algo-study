import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        Deque<Integer>[] deques = new Deque[7];
        for (int i = 1; i <= 6; i++) {
            deques[i] = new ArrayDeque<>();
        }
        int moves = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            while (!deques[s].isEmpty() && deques[s].peek() > p) {
                deques[s].pop();
                moves++;
            }
            if (!deques[s].isEmpty() && deques[s].peek() == p) continue;
            deques[s].push(p);
            moves++;
        }
        System.out.println(moves);
    }
}