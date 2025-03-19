import java.util.*;

public class BOJ2644촌수계산 {
    static int n, m, a, b;
    static List<Integer>[] graph;
    static boolean[] visited;
    public static void main(String[] args) {
        // a 정점과 b 정점 사이에 연결된 최소 간선의 수(최단 거리)
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n+1];

        for(int i = 1; i <= n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x].add(y);
            graph[y].add(x);
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {a, 0});  // {정점, 깊이(촌수)}
        visited = new boolean[n+1];
        visited[a] = true;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            // b 노드에 도달하면 촌수 리턴
            if(cur[0] == b) return cur[1];

            for(int next : graph[cur[0]]){
                if(visited[next]) continue;

                visited[next] = true;
                q.offer(new int[] {next, cur[1]+1});
            }
        }

        // 정점이 연결되지 않은 경우 -1 리턴
        return -1;
    }
}
