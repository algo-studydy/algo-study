import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0801 {
    static int N, M;
    static ArrayList<Integer> list;
    static StringBuilder sb;

    static void go(int curIdx, int selected) {
        if (selected == M) {
            for (int i=0;i<M;i++) {
                sb.append(list.get(i));
                if (i != M-1) sb.append(" ");
            }sb. append("\n");
            return;
        }

        for (int i=curIdx;i<=N;i++) {
            list.add(i);
            go(i + 1, selected+1);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        list = new ArrayList<>();

        go(1, 0);

        System.out.print(sb.toString().trim());
    }
}
