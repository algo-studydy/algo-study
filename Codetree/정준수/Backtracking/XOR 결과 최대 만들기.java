import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ct0802 {
    static int N, M, ans;
    static int[] map;
    static ArrayList<Integer> list;

    static void go(int currIdx, int count) {
        if (count == M) {
            int result = list.get(0);
            for (int i=1;i<list.size();i++) {
                result ^= list.get(i);
            }

            ans = Math.max(ans, result);
            return;
        }

        if (currIdx >= N) return;

        int num = map[currIdx];

        // 정수 선택
        list.add(num);
        go(currIdx + 1, count + 1);
        list.remove(list.size()-1);

        // 미선택
        go(currIdx + 1, count);
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        go(0, 0);

        System.out.print(ans);
    }
}
