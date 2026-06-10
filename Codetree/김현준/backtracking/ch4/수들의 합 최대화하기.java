
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
    static int max = 0;
    static List<Integer> list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N];
        map = new int[N][N];
        list = new ArrayList<>();
        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        choose(0);
        System.out.println(max);
    }

    private static void choose(int idx) {
        // basis
        if(idx == N) {
            max = Math.max(max, calculate());
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

    private static int calculate() {
        int sum = 0;
        for(int i : list) sum += i;
        return sum;
    }

}
