
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] v;
    static int[][] map;
    static int max = Integer.MIN_VALUE;
    static List<Integer> list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        v = new boolean[N];
        list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        choose(0);
        System.out.println(max);
    }

    private static void choose(int idx) {
        // basis
        if(idx == N) {
            max = Math.max(max, findMin());
            return;
        }
        // inductive
        for(int i=0;i<N;i++) {
            if(!v[i]) {
                v[i] = true;
                list.add(map[idx][i]);
                choose(idx + 1);
                list.remove(list.size() - 1);
                v[i] = false;
            }
        }

    }

    private static int findMin() {
        int num = Integer.MAX_VALUE;
        for(int i : list) {
            num = Math.min(num, i);
        }
        return num;
    }
}
