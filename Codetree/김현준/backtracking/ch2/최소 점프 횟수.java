
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> list;
    static int min = Integer.MAX_VALUE;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        choose(0, 0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static void choose(int curr, int cnt) {
        // basis
        if(curr >= N-1) {
            min = Math.min(min, cnt);
            return;
        }

        // inductive
        int range = list.get(curr);
        for(int i=1;i<=range;i++) {
            choose(curr + i, cnt + 1);
        }
    }

}
