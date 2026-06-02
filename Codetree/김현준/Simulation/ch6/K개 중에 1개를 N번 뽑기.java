import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> list;
    static int N, M;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        recursive(0);
    }

    private static void recursive(int idx) {
        // basis
        if(idx == M) {
            for(int i : list) System.out.print(i + " ");
            System.out.println();
            return;
        }
        // inductive
        for(int i=1;i<=N;i++) {
            list.add(i);
            recursive(idx + 1);
            list.remove(list.size() - 1);
        }
    }
}
