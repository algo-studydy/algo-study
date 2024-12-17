import java.util.*;

public class Main {
    static class Vertex {
        int v, w;
        public Vertex(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private static final long INF = Long.MAX_VALUE;
    static int N, M;
    static List<Vertex>[] list;
    static long[] dist;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        // 최단거리 배열 초기화
        list = new ArrayList[N];
        dist = new long[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
            dist[i] = INF;
        }

        // 시작도시, 도착도시, 시간
        for (int i = 0; i < M; i++) {
            int A = sc.nextInt() - 1;
            int B = sc.nextInt() - 1;
            int C = sc.nextInt();
            list[A].add(new Vertex(B, C));
        }

        // 벨만포드
        if (!bellmanFord(0)) {
            // 음수 사이클 존재 시 -1 출력
            System.out.println(-1);
        } else {
            // 결과출력
            for (int i = 1; i < N; i++) {
                if (dist[i] == INF) System.out.println(-1);
                else System.out.println(dist[i]);
            }
        }
    }

    static boolean bellmanFord(int start) {
        dist[start] = 0;

        // 점점의갯수-1 만큼 반복
        for (int i = 0; i < N - 1; i++) {
            for (int u = 0; u < N; u++) {
                // 도달 불가능한 정점
                if (dist[u] == INF) continue;
                for (Vertex edge : list[u]) {
                    int v = edge.v;
                    int w = edge.w;
                    if (dist[u] != INF && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
        }

        // 한번더 반복해서 값이 줄어들면 음수 사이클
        for (int u = 0; u < N; u++) {
            if (dist[u] == INF) continue;
            for (Vertex edge : list[u]) {
                int v = edge.v;
                int w = edge.w;
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    // 음수 사이클 존재
                    return false;
                }
            }
        }

        return true;
    }
}
