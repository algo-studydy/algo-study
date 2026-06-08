
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer> list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        choose(0, 1);
    }

    private static void choose(int curr, int idx) {
        // basis
        if(curr == M) {
            print();
            return;
        }
        // inductive
        for(int i=idx;i<=N;i++) {
            list.add(i);
            choose(curr + 1, i + 1);
            list.remove(list.size() - 1);
        }

    }

    private static void print() {
        for(int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
