import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int[] arr = new int[A];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> tail = new ArrayList<>();
        for (int x : arr) {
            int pos = Collections.binarySearch(tail, x);
            if (pos < 0) {
                pos = -pos - 1;
            }

            if (pos >= tail.size()) {
                tail.add(x);
            } else {
                tail.set(pos, x);
            }
        }

        System.out.println(tail.size());
    }
}
