import java.util.*;

public class Main {
    static int V;
    static int E;
    static int R;
    static ArrayList<Integer>[] adjList;
    static int[] visit;
    static boolean[] visited;
    static int visitedTime = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        R = sc.nextInt();
        adjList = new ArrayList[V + 1];

        for (int i = 0; i < V + 1; i++){
            adjList[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for(int i = 0; i < V + 1; i++){
            Collections.sort(adjList[i], Collections.reverseOrder());
        }
        visit = new int[V + 1];
        visited = new boolean[V + 1];
        bfs();

        for(int i = 1; i < V + 1; i++){
            System.out.println(visit[i]);
        }
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        visited[R] = true;
        q.add(R);
        while(!q.isEmpty()){
            int v = q.poll();
            visit[v] = visitedTime++;
            for(int i : adjList[v]){
                if(!visited[i]){
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }
}
