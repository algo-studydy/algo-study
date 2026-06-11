
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
    static int[] arr;
    static int max = 0;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        choose(0, 0);
        System.out.println(max);
    }

    private static void choose(int curr, int idx) {
        // basis
        if(curr == M) {
            max = Math.max(max, find());
            return;
        }
        // inductive
        for(int i=idx;i<N;i++) {
            list.add(arr[i]);
            choose(curr + 1, i + 1);
            list.remove(list.size() - 1);
        }
    }

    private static int find() {
        int num = 0;
        for(int i : list) {
            num = num ^ i;
        }
        return num;
    }

    // 우선, 중복 뽑기는 안되기에 조합으로 뽑고 뽑은 수를 xor 해서 나올 수 있는 최대값 뽑기
}
