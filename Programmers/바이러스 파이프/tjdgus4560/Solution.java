import java.util.*;

class Solution {

    public class Edge{
        int to;
        int pipe;

        Edge(int to, int pipe){
            this.to = to;
            this.pipe = pipe;
        }
    }

    List<List<Edge>> adjList;
    int k;
    int answer;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.k = k;

        adjList = new ArrayList<>();
        for(int i=0; i<=n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            int type = edge[2];

            adjList.get(from).add(new Edge(to, type));
            adjList.get(to).add(new Edge(from, type));
        }

        boolean[] visited = new boolean[n + 1];
        visited[infection] = true;

        answer = 1;
        re(0, visited, 0);

        return answer;
    }

    public void re(int level, boolean[] visited, int type){
        answer = Math.max(answer, countAnswer(visited));

        if(level == k) return;



        for(int nextType=1; nextType<=3; nextType++){
            if(type == nextType) continue; // 같은타입이면 넘기기

            re(level+1, bfs(visited, nextType), nextType);
        }
    }

    public boolean[] bfs(boolean[] visited, int type){
        boolean[] v = visited.clone();

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<v.length; i++){
            if(v[i]) q.offer(i);
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(Edge e : adjList.get(cur)){
                if(e.pipe == type && !v[e.to]){
                    q.offer(e.to);
                    v[e.to] = true;
                }
            }
        }

        return v;
    }
    public int countAnswer(boolean[] visited){
        int cnt = 0;
        for(boolean v : visited){
            if(v) cnt++;
        }
        return cnt;
    }
}