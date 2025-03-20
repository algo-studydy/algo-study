import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point {
        int idx, cnt;
        Point(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    static int N, M;
    static List<Integer>[] list;
    static boolean[] v;
    static int answer = 0;
    static int f, s;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        v = new boolean[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int child = Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            list[child].add(parent);
            list[parent].add(child);
        }

        v[f] = true;
//        find(f, s, 0);
        another_find(f, s);
        if(answer != 0) System.out.println(answer);
        else System.out.println(-1);
    }

    private static void find(int start, int end, int cnt) {

        if(start == end) {
            answer = cnt;
            return;
        }

        for(int i : list[start]) {
            if(!v[i]) {
                v[i] = true;
                find(i, end, cnt + 1);
                v[i] = false;
            }
        }
    }

    private static void another_find(int start, int end) {
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(start, 0));
        while(!Q.isEmpty()) {
            Point p = Q.poll();
            if(p.idx == end) {
                answer = p.cnt;
                break;
            }

            for(int i : list[p.idx]) {
                if(!v[i]) {
                    v[i] = true;
                    Q.offer(new Point(i, p.cnt + 1));
                }
            }
        }
    }
}