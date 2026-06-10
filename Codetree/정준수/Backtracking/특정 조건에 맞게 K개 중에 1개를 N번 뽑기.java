import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ct0701 {
    static int K, N;
    static ArrayList<Integer> list;
    static StringBuilder sb;

    static void buildAnswer() {
        for (int i=0;i<N;i++) {
            sb.append(list.get(i));
            if (i != N-1) sb.append(" ");
        }
        sb.append("\n");
    }

    static void go(int selectedCount) {
        if (selectedCount == N) {
            buildAnswer();
            return;
        }

        for(int num=1;num<=K;num++) {
            if (list.size() >= 2) {
                int front = list.get(list.size()-1);
                int frontfront = list.get(list.size() - 2);

                if (front == num && frontfront == num) {
                    continue;
                }
            }

            list.add(num);
            go(selectedCount+1);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        sb = new StringBuilder();
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        go(0);

        System.out.print(sb.toString().trim());
    }
}
