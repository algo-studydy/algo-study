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

    static int V, E;
    static int[] parent;
    static List<Point> edges = new ArrayList<Point>();
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];
        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Point(u, v, weight));
        }

        System.out.println(kruskal());
    }

    public static int kruskal() {
        int sum = 0, cnt = 0;
        Collections.sort(edges); // 간선 정렬

        Arrays.fill(parent, -1);

        for(Point p : edges) {
            if(cnt == V-1) break; // MST 에서 계산중인 간선 갯수가 정점보다 1개 작으면 종료

            if(union(p.u, p.v)) {
                sum += p.weight;
                cnt++;
            }
        }

        return sum;
    }

    // [-1,-3, 1, 1,-1]
    // union (1, 2) // 1, 2  1 => -2, 2 = 1
    // union (2, 3) // 1 -1 , parent[1] += parent[3]

    public static int find(int x) {
        if(parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    public static boolean union(int x, int y) {
        int u = find(x);
        int v = find(y);

        if(u == v) return false; // 이미 같은 루트를 지님
//        if(parent[u] > parent[v]) { // u, v 중 u 의 연결 갯수가 더 적다면, 자리를 바꿔주고 union 을 진행
//            int temp = u;
//            u = v;
//            v = temp;
//        }

        parent[u] += parent[v]; // 연결 갯수를 합침
        parent[v] = u; // 그리고 v의 연결 갯수를 합쳤으니, 부모를 가르키게 설정
        return true;
    }
}