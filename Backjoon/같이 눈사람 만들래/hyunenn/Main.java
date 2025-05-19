import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        int x, y, sum;
        public Pair(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        @Override
        public int compareTo(Pair p) {
            return sum - p.sum;
        }
    }
    static int N;
    static List<Pair> list = new ArrayList<>();
    static StringTokenizer st;
    static int[] snows;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        snows = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snows[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0;i<N;i++) {
            for(int j=i+1;j<N;j++) {
                list.add(new Pair(i, j, snows[i] + snows[j]));
            }
        }

        Collections.sort(list);

        int answer = Integer.MAX_VALUE;

        for(int i=0;i<list.size();i++) {
            for(int j=i+1;j<list.size();j++) {
                Pair p = list.get(i);
                Pair p2 = list.get(j);

                // 고른 값이 전부 다르고, 최소값의 갱신이 가능하다면 변경
                if(p.x != p2.x && p.x != p2.y && p.y != p2.x && p.y != p2.y) {
                    int sum = p2.sum - p.sum;
                    answer = Math.min(answer, sum);
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
