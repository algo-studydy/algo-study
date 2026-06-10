import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0902 {
    static int N;
    static boolean[] visited;
    static ArrayList<Integer> list;
    static StringBuilder sb;

    static void go(int count) {
        if (count == N) {
            for (int i=0;i<N;i++) {
                sb.append(list.get(i));
                if(i!=N-1) sb.append(" ");
            }sb.append("\n");
            return;
        }

        for (int i=N;i>=1;i--) {
            if (visited[i]) continue;
            visited[i] = true;
            list.add(i);
            go(count + 1);
            list.remove(list.size()-1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N+1];
        sb = new StringBuilder();
        list = new ArrayList<>();

        go(0);

        System.out.print(sb.toString().trim());
    }
}
