import java.util.*;

public class Main {
    static class Edge {
        int to, weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    static int N;
    static ArrayList<ArrayList<Edge>> adjlist;
    static boolean[] visited;
    static int max = 0; //최장거리
    static int tmpNode = 0; //루트에서 가장 먼 노드 저장용

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        adjlist = new ArrayList<>();

        for(int i=0; i<=N; i++){
            adjlist.add(new ArrayList<>());
        }

        for(int i=0; i<N-1; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            adjlist.get(from).add(new Edge(to, weight));
            adjlist.get(to).add(new Edge(from, weight));
        }

        // 루트노드에서 가장 먼노드 찾기
        visited = new boolean[N+1];
        dfs(1,0);

        // 찾은 노드에서 가장 먼 노드 찾기
        visited = new boolean[N+1];
        max = 0;
        dfs(tmpNode,0);

        System.out.println(max);
    }

    static void dfs(int node, int dist){
        visited[node] = true;

        if(dist > max){
            max = dist;
            tmpNode = node;
        }

        for(Edge edge : adjlist.get(node)){
            if(!visited[edge.to]){
                dfs(edge.to, dist + edge.weight);
            }
        }
    }
}