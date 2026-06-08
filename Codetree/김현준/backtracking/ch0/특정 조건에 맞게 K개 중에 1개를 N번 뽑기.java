
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static StringTokenizer st;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        choose(0, 0);
    }

    private static void choose(int curr, int cnt) {
        // basis
        if (curr == N) {
            print();
            return;
        }
        // inductive
        for (int i = 1; i <= K; i++) {
            if(!list.isEmpty() && cnt == 2 && list.get(list.size() - 1) == i) continue;

            int nextCnt;

            if(!list.isEmpty() && list.get(list.size() - 1) == i) {
                nextCnt = cnt + 1;
            } else nextCnt = 1;

            list.add(i);
            choose(curr + 1, nextCnt);
            list.remove(list.size() - 1);
        }
    }

    private static void print() {
        for (int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
