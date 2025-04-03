import java.util.*;

public class BOJ1238파티 {
    static int n, m, x;
    static List<int[]>[] map, reverseMap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); // 마을 개수
        m = sc.nextInt(); // 도로 개수
        x = sc.nextInt(); // 파티 장소

        // 그래프 초기화
        map = new ArrayList[n + 1];
        reverseMap = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            map[i] = new ArrayList<>();
            reverseMap[i] = new ArrayList<>();
        }

        // 간선 입력
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            map[u].add(new int[]{v, w});
            reverseMap[v].add(new int[]{u, w}); // 역방향 그래프
        }
        sc.close();

        // 1️⃣ 모든 마을에서 X까지의 최단 거리
        int[] distToX = dijkstra(map, x);

        // 2️⃣ X에서 모든 마을로 돌아오는 최단 거리
        int[] distFromX = dijkstra(reverseMap, x);

        // 3️⃣ 최대 왕복 시간 계산
        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, distToX[i] + distFromX[i]);
        }

        System.out.println(maxTime);
    }

    // 다익스트라 알고리즘 (배열만 사용)
    public static int[] dijkstra(List<int[]>[] graph, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int now = cur[0];
            int nowCost = cur[1];

            if (nowCost > dist[now]) continue;

            for (int[] next : graph[now]) {
                int nextNode = next[0];
                int nextCost = nowCost + next[1];

                if (nextCost < dist[nextNode]) {
                    dist[nextNode] = nextCost;
                    pq.offer(new int[]{nextNode, nextCost});
                }
            }
        }
        return dist;
    }
}
