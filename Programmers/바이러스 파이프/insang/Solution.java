import java.util.*;

class Solution {
    static List<int[]>[] graph;
    static int ans;

    public int solution(int n, int infection, int[][] edges, int k) {
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            graph[e[1]].add(new int[]{e[0], e[2]});
        }

        // 초기 감염 노드
        boolean[] visited = new boolean[n+1];
        visited[infection] = true;
        ans = 1;

        dfs(visited, 1, k, n);
        return ans;
    }

    // 파이프 중 하나를 선택
    void dfs(boolean[] visited, int cnt, int k, int n) {
        ans = Math.max(ans, cnt);
        if (k == 0) return;

        for (int i = 1; i <= 3; i++) {
            // 현재 상태 복사 후 해당 종류 파이프로 감염 확산
            boolean[] next = Arrays.copyOf(visited, n+1);
            int nextCnt = bfs(next, i);

            // 새로 감염된 노드가 있을 때만 다음 단계 진행
            if (nextCnt > cnt) {
                dfs(next, nextCnt, k-1, n);
            }
        }
    }

    // 현재 감염된 노드에서 특정 종류 파이프만 타고 퍼질 수 있는 노드탐색
    int bfs(boolean[] visited, int pipe) {
        Queue<Integer> q = new LinkedList<>();

        // 이미 감염된 노드들을 시작점으로 넣기
        for (int i = 1; i < visited.length; i++) {
            if (visited[i]) q.add(i);
        }

        int cnt = 0;
        for (boolean v : visited) if (v) cnt++;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int[] edge : graph[cur]) {
                // 같은 종류 파이프이고 아직 감염되지 않은 노드면 감염
                if (edge[1] == pipe && !visited[edge[0]]) {
                    visited[edge[0]] = true;
                    q.add(edge[0]);
                    cnt++;
                }
            }
        }
        return cnt;
    }
}