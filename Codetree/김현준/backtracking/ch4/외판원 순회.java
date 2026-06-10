
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
    static int min = Integer.MAX_VALUE;
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
        choose(0, 0);
        System.out.println(min);
    }

    private static void choose(int idx, int curr) {
        // basis
        if(idx == N) {
            if(calculate() < min) {
                min = calculate();
//                System.out.println("max = " + min);
//                for(int i : list) {
//                    System.out.print(i + " ");
//                }
//                System.out.println();
            }

            return;
        }
        // inductive

        for(int i=0;i<N;i++) {
            if(i == 0 && idx != N-1) continue;

            if(!v[i] && map[curr][i] != 0) {
                v[i] = true;
                list.add(map[curr][i]);
                choose(idx + 1, i);
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
