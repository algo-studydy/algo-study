
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
    static List<Integer> list;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        v = new boolean[N+1];
        list = new ArrayList<>();
        choose(0);
    }

    private static void choose(int idx) {
        // basis
        if(idx == N) {
            print();
            return;
        }
        // inductive
        for(int i=N;i>=1;i--) {
            if(!v[i]) {
                v[i] = true;
                list.add(i);
                choose(idx + 1);
                v[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    private static void print() {
        for(int i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

}
