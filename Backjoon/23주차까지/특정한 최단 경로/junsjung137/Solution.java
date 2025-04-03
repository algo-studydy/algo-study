import java.util.*;
import java.io.*;

public class Main {
    static HashMap<Integer, HashMap<Integer, Integer>> hMap;
    static int n, e;
    static int ans;
    static int V1, V2;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        hMap = new HashMap<>();
        ans = -1;
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            hMap.put(i, new HashMap<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            hMap.get(u).put(v, d);
            hMap.get(v).put(u, d);
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        int OneToV1 = go(1, V1);
        int OneToV2 = go(1, V2);
        int V1ToV2 = go(V1, V2);
        int V1ToN = go(V1, n);
        int V2ToN = go(V2, n);

        if (OneToV1 == -1 || OneToV2 == -1 || V1ToV2 == -1 || V1ToN == -1 || V2ToN == -1) {
            ans = -1;
        } else {
            int path1 = OneToV1 + V1ToV2 + V2ToN;
            int path2 = OneToV2 + V1ToV2 + V1ToN;
            ans = Math.min(path1, path2);
        }

        System.out.print(ans);
    }

    static int go(int start, int target) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int currentVertex = current.vertex;
            int currentCost = current.cost;

            if (currentVertex == target) {
                return currentCost;
            }

            if (currentCost > dist[currentVertex]) {
                continue;
            }

            for (Map.Entry<Integer, Integer> entry : hMap.get(currentVertex).entrySet()) {
                int neighbor = entry.getKey();
                int edgeCost = entry.getValue();
                if (dist[neighbor] > dist[currentVertex] + edgeCost) {
                    dist[neighbor] = dist[currentVertex] + edgeCost;
                    pq.offer(new Node(neighbor, dist[neighbor]));
                }
            }
        }

        return -1;
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
