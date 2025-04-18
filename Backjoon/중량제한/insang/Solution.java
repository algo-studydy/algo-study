import java.util.*;

public class BOJ1939중량제한 {
    static int n, m;
    static List<int[]>[] graph;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int maxW = 0;

        // 그래프 구성: 각 int[]는 {연결된 노드, 중량 제한}
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
            maxW = Math.max(maxW, c);
        }

        int start = sc.nextInt();
        int end = sc.nextInt();

        // 이분 탐색으로 최대 운반 중량 탐색
        int left = 1;
        int right = maxW;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (bfs(start, end, mid)) {
                answer = mid;      // 이동 가능하면 정답 후보
                left = mid + 1;    // 더 무거운 중량도 가능할지 탐색
            } else {
                right = mid - 1;   // 중량 줄이기
            }
        }

        System.out.println(answer);
    }

    // BFS로 현재 무게(limit)를 실었을 때 start ~ end 경로 존재 여부 탐색
    static boolean bfs(int start, int end, int limit) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            // 도착 섬에 도달하면 true 리턴
            if (cur == end) return true;

            // 현재 섬과 연결된 모든 다리 탐색
            for (int[] edge : graph[cur]) {
                int next = edge[0];
                int weight = edge[1];

                // 방문한적 없고, 중량 제한을 만족하는 경우
                if (!visited[next] && weight >= limit) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

        return false;  // 도착 섬에 도달할 수 없으면 false 리턴
    }
}
