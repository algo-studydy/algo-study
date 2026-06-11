
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] arr;
    static StringTokenizer st;
    static List<Integer> list;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        list = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];
        Arrays.fill(arr, 1);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        choose(0);
        System.out.println(max);
    }

    // N 턴 만큼 진행했을때, arr 의 각 배열이 M까지 도달할 수 있는 최대 갯수
    private static void choose(int curr) {

        int cnt = 0;
        for(int i=0;i<K;i++) {
            if(arr[i] >= M) cnt++;
        }
        max = Math.max(cnt, max);
        // basis
        if (curr == N) {
            return;
        }

        // inductive
        for(int i=0;i<K;i++) {
            if(arr[i] >= M) continue;

            arr[i] += list.get(curr);
            choose(curr + 1);
            arr[i] -= list.get(curr);
        }
    }

}
