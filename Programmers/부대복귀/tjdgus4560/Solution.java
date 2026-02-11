import java.util.*;

class Solution {
    ArrayList<ArrayList<Integer>> adjlist;
    int[] visited;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        int[] answer = new int[sources.length];
        adjlist = new ArrayList<>();
        for(int i=0; i<=n ;i++){
            adjlist.add(new ArrayList<>());
        }


        for(int[] road : roads){
            int from = road[0];
            int to = road[1];

            adjlist.get(from).add(to);
            adjlist.get(to).add(from);
        }

        visited = new int[n+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        bfs(destination);

        for(int i=0; i<sources.length; i++){
            answer[i] = visited[sources[i]] != Integer.MAX_VALUE ? visited[sources[i]] : -1;
        }
        return answer;
    }

    public void bfs(int start){
        visited[start] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        int dist =1;
        while(!q.isEmpty()){
            int size = q.size();

            while(size-->0){
                int cur = q.poll();

                ArrayList<Integer> list = adjlist.get(cur);
                for(int next : list){
                    if(visited[next] > dist){
                        visited[next] = dist;
                        q.add(next);
                    }
                }
            }
            dist++;
        }
    }
}