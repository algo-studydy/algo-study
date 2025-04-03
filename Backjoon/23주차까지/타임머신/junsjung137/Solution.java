import java.util.*;
import java.io.*;

public class Main {
    static class Edge {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    static int V, E, K;
    static List<Edge> edgeList;
    static long[] dist;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = 1;
        edgeList = new ArrayList<>();
        dist = new long[V + 1];

        // 그래프 초기화
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(u, v, w));
        }

        // dist 배열 초기화
        Arrays.fill(dist, INF);
        dist[K] = 0;

        // bellman-ford 알고리즘
        if (bellmanFord()) {
            System.out.println("-1");
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 2; i <= V; i++) {
                long cost = dist[i];
                if (cost < INF) bw.write(cost + "");
                else bw.write("-1");
                bw.write("\n");
            }
            bw.flush();
        }
    }

    static boolean bellmanFord() {
        for (int i = 0; i < V; i++) {
            for (Edge e : edgeList) {
                if (dist[e.u] != INF && dist[e.v] > dist[e.u] + e.w) {
                    dist[e.v] = dist[e.u] + e.w;
                    if (i == V - 1) return V > 2;
                }
            }
        }
        return false;
    }
}
