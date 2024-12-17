import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static Queue<Integer> q = new ArrayDeque<>();
    static int cnt = 1;
    static int[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 정점의 수
        int m = sc.nextInt();  // 간선의 수
        int r = sc.nextInt();  // 시작 정점
        visited = new int[n+1];

        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            list.get(u).add(v);
            list.get(v).add(u);
        }

        // 리스트 내림차순 정렬
        for (ArrayList<Integer> node : list) {
            Collections.sort(node, Collections.reverseOrder());
        }

        q.add(r);
        visited[r] = cnt++;
        bfs();

        for(int i = 1; i < visited.length; i++){
            System.out.println(visited[i]);
        }
    }

    private static void bfs() {
        while(!q.isEmpty()){
            int node = q.poll();  // 현재 탐색할 노드

            // 현재 노드와 연결된 노드 중 방문한적 없는 노드를 큐에 담음
            for(int i = 0; i < list.get(node).size(); i++){
                int n = list.get(node).get(i);

                if(visited[n] == 0){
                    q.add(n);
                    visited[n] = cnt++;
                }
            }
        }

    }
}
