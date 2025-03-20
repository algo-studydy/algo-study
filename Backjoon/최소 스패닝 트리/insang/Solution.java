import java.io.*;
import java.util.*;

public class Main {
    static int v, e;
    static boolean[] visited;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken()); // 정점 개수
        e = Integer.parseInt(st.nextToken()); // 간선 개수

        graph = new ArrayList[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }

        System.out.println(prim());
    }

    static int prim() {
        // 우선순위 큐 가중치 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        visited = new boolean[v + 1];

        pq.offer(new int[]{1, 0});  // {정점, 가중치}
        int result = 0;  // 누적된 총 비용
        int cnt = 0;  // MST에 포함된 정점의 개수

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int vertex = current[0];
            int weight = current[1];

            if (visited[vertex]) continue; // 이미 MST에 포함된 정점이면 스킵

            visited[vertex] = true;
            result += weight;
            cnt++;

            if (cnt == v) break; // 모든 정점 방문 시 break

            for (int[] next : graph[vertex]) {
                if (!visited[next[0]]) {
                    pq.offer(next); // 연결된 간선 추가
                }
            }
        }

        return result;
    }
}
