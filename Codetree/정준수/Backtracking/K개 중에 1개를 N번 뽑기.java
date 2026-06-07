import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0601 {
    static int K, N;
    static StringBuilder sb;
    static ArrayList<Integer> list;

    static void addListString() {
        int size = list.size();
        for (int i=0;i<size;i++) {
            sb.append(list.get(i));
            if (i != size - 1) sb.append(" ");
        }
        sb.append("\n");
    }

    static void go(int idx) {
        if (idx == N) {
            addListString();
            return;
        }

        for (int number=1;number<=K;number++) {
            list.add(number);
            go(idx + 1);
            list.remove(idx);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

       st = new StringTokenizer(br.readLine());
       K = Integer.parseInt(st.nextToken());
       N = Integer.parseInt(st.nextToken());
       sb = new StringBuilder();
       list = new ArrayList<>();

       go(0);

       System.out.print(sb.toString().trim());
    }
}
