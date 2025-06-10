import java.util.*;
import java.io.*;

public class Main {
    static class Point {
        int next, size;
        Point(int next, int size) {
            this.next = next;
            this.size = size;
        }
    }
    static int N;
    static int farNode = 1;
    static int max = 0;
    static boolean[] v;
    static List<Point>[] list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Point(b, c));
            list[b].add(new Point(a, c));
        }

        v = new boolean[N + 1];
        v[1] = true;
        dfs(farNode, 0);

        v = new boolean[N + 1];
        max = 0;
        v[farNode] = true;
        dfs(farNode, 0);

        System.out.println(max);
    }

    // 1. 가장 먼 노드를 탐색
    // 2. 그 노드에서 또 가장 먼 노드를 탐색하면 가장 긴 노드가 탄생한다.
    public static void dfs(int idx, int size) {
        // basis
        if(size > max) {
            max = size;
            farNode = idx;
        }
        // inductive
        for(Point p : list[idx]) {
            if(!v[p.next]) {
                v[p.next] = true;
                dfs(p.next, size + p.size);
                v[p.next] = false;
            }
        }
    }
}
