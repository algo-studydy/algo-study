import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] check;
    static String answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트케이스 수

        for(int i = 0; i < T; i++){
            graph = new ArrayList<ArrayList<Integer>>();
            int v = sc.nextInt();  // 정점의 개수
            int e = sc.nextInt();  // 간선의 개수

            // 1~v 까지의 노드 생성
            for(int j = 0; j <= v; j++){
                graph.add(new ArrayList<Integer>());
            }

            // 간선 정보 저장
            for(int j = 0; j < e; j++){
                int n = sc.nextInt();
                int m = sc.nextInt();
                graph.get(n).add(m);
                graph.get(m).add(n);
            }

            check = new int[v+1];  // 그룹핑을 통한 이분 그래프 판별을 위해 배열 생성
            answer = "YES";

            // 모든 노드가 연결되어 있지 않을 수 있으므로, 방문한적 없는 모든 노드에 대해 DFS
            for(int j = 1; j <= v; j++){
                if(check[j] == 0) dfs(j, 1);
            }

            System.out.println(answer);
        }
    }

    private static void dfs(int n, int group) {
        check[n] = group;
        for(int i = 0; i < graph.get(n).size(); i++){
            int node = graph.get(n).get(i);

            // 인접한 노드의 그룹이 정해지지 않았을 경우, 현재 노드의 반대 그룹으로 지정
            if(check[node] == 0) dfs(node, group * -1);

            // 현재 노드가 인접한 노드와 같은 그룹이면 이분 그래프가 아님
            if(check[node] == check[n]){
                answer = "NO";
                return;
            }
        }
    }
}
