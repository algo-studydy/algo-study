import java.util.*;
import java.io.*;

public class Main {
    static class Vertex implements Comparable<Vertex> {
        int number;
        int cost;

        Vertex(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static int V, E, K;
    static List<Vertex>[] adjList;
    static int[] dist;
    static boolean[] visited;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        dist = new int[V + 1];

        // 인접리스트 초기화
        for (int i = 0; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        // 그래프 초기화
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adjList[u].add(new Vertex(v, w));
        }

        // dist 배열 초기화
        Arrays.fill(dist, INF);
        dist[K] = 0;

        // dijkstra 알고리즘
        dijkstra();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 1; i <= V; i++) {
            int cost = dist[i];
            if (cost < INF) bw.write(cost+"");
            else bw.write("INF");

            bw.write("\n");
        }
        bw.flush();
    }

    static void dijkstra() {
        PriorityQueue<Vertex> pQ = new PriorityQueue<>();
        pQ.add(new Vertex(K, 0));

        while (!pQ.isEmpty()) {
            Vertex u = pQ.poll();
            int uNum = u.number;

            if (visited[uNum]) continue;
            visited[uNum] = true;

            List<Vertex> neighbors = adjList[u.number];
            for (Vertex v : neighbors) {
                int vNum = v.number;
                int cost = dist[uNum] + v.cost;

                if (dist[vNum] > cost) {
                    dist[vNum] = cost;
                    pQ.add(new Vertex(vNum, dist[vNum]));
                }

            }
        }
    }
}
