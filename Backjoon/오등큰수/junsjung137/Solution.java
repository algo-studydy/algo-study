import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] A = new int[N];
        int[] freq = new int[1_000_001];
        int[] NGF = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            freq[A[i]]++;
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!deque.isEmpty() && freq[A[deque.peekLast()]] < freq[A[i]]) {
                NGF[deque.pollLast()] = A[i];
            }
            deque.offerLast(i);
        }

        while (!deque.isEmpty()) {
            NGF[deque.pollLast()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(NGF[i]);
            if (i < N - 1) sb.append(" ");
        }
        System.out.println(sb.toString());
    }
}