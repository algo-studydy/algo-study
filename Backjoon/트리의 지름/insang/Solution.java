import java.util.*;

public class BOJ1967트리의지름 {
    static ArrayList<int[]>[] tree;
    static boolean[] visited;
    static int maxDist = 0;
    static int farNode = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if(n == 1){
            System.out.println(0);
            return;
        }

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < n-1; i++) {
            int parent = sc.nextInt();
            int child = sc.nextInt();
            int weight = sc.nextInt();

            tree[parent].add(new int[]{child, weight});
            tree[child].add(new int[]{parent, weight});
        }

        // 임의의 노드에서 가장 먼 노드 찾기
        // 1번 노드느 항상 존재하므로, 1번 노드에서 탐색
        visited = new boolean[n + 1];
        dfs(1, 0);

        // 그 노드에서 다시 가장 먼 거리 계산
        visited = new boolean[n + 1];
        maxDist = 0;
        dfs(farNode, 0);

        System.out.println(maxDist);
    }

    static void dfs(int cur, int dist) {
        visited[cur] = true;

        // 현재까지 가장 먼 거리라면 갱신
        if (dist > maxDist) {
            maxDist = dist;
            farNode = cur;
        }

        // 현재 노드에서 연결된 노드들로 DFS 탐색
        for (int[] edge : tree[cur]) {
            int next = edge[0];  // 다음 노드
            int weight = edge[1];  // 현재 노드와 다음 노드 사이 거리
            if (!visited[next]) {
                dfs(next, dist + weight);
            }
        }
    }
}
