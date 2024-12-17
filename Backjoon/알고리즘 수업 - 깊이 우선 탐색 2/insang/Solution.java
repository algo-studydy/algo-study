import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
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

        dfs(r);

        for(int i = 1; i < visited.length; i++){
            System.out.println(visited[i]);
        }
    }

    private static void dfs(int n) {
        visited[n] = cnt++;  // n번 노드의 방문 순서 기록

        for(int i = 0; i < list.get(n).size(); i++){
            int node = list.get(n).get(i);

            if(visited[node] == 0){
                dfs(node);
            }
        }
    }
}
