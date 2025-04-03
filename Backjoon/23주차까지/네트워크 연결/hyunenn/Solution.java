import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        int u, v, weight;
        Point(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point p) {
            return this.weight - p.weight;
        }
    }
    static int N, M;
    static int[] parent;
    static List<Point> edges = new ArrayList<>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정점
        M = Integer.parseInt(br.readLine()); // 간선
        parent = new int[N+1];
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Point(u, v, weight));
        }

        System.out.println(kruscal());
    }

    public static int kruscal() {
        int sum = 0, cnt = 0;
        Collections.sort(edges);

        Arrays.fill(parent, -1);

        for(Point p : edges) {
            if(cnt == N - 1) break;
            if(union(p.u, p.v)) {
                sum += p.weight;
                cnt++;
            }
        }

        return sum;
    }

    public static int find(int x) {
        if(parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        int u = find(x);
        int v = find(y);

        if(u == v) return false;
        if(parent[u] > parent[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        parent[u] += parent[v];
        parent[v] = u;
        return true;
    }
}
