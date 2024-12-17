import java.util.*;

public class Main {
    static ArrayList<int[]>[] graph;
    static int[] dist; // 시작점에서 각 노드까지의 최단 거리 배열
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();  // 정점의 개수
        int E = sc.nextInt();  // 간선의 개수
        int K = sc.nextInt();  // 시작 정점

        graph = new ArrayList[V+1];
        dist = new int[V+1];
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>(); // 각 노드의 인접 리스트 초기화
            dist[i] = Integer.MAX_VALUE; // 모든 노드까지의 거리를 무한대로 초기화
        }

        for(int i = 0; i < E; i++){
            int u = sc.nextInt(); // 정점 u
            int v = sc.nextInt();  // 정점 v
            int w = sc.nextInt();  // u -> v 가중치
            graph[u].add(new int[] {v, w});
        }

        // 우선순위 큐 사용: {노드, 누적 거리}를 저장하며 거리 기준 오름차순 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{K, 0}); // 시작 노드를 큐에 추가 (시작점의 거리 = 0)
        dist[K] = 0; // 시작점의 최단 거리는 0

        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); // 우선순위 큐에서 현재 노드를 꺼냄
            int curN = cur[0]; // 현재 노드
            int curW = cur[1]; // 현재 노드까지의 누적 거리

            // 현재 저장된 거리보다 큰 경우는 무시
            if (curW > dist[curN]) continue;

            // 현재 노드와 연결된 모든 간선 확인
            for (int[] edge : graph[curN]) {
                int nextN = edge[0]; // 다음 노드
                int nextW = curW + edge[1]; // 다음 노드까지의 거리 = 현재 거리 + 가중치

                // 더 짧은 경로를 찾은 경우 최단 거리 업데이트
                if (nextW < dist[nextN]) {
                    dist[nextN] = nextW;
                    pq.add(new int[]{nextN, nextW});
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
        }
    }
}
