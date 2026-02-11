import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<Integer>());
        }

        for(int[] road : roads){
            int from = road[0];
            int to = road[1];
            list.get(from).add(to);
            list.get(to).add(from);
        }

        // 거리 배열 초기화
        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(destination);
        dist[destination] = 0;  // 목적지의 최단거리 0

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : list.get(cur)) {
                // 아직 방문하지 않은 지역이라면
                // System.out.println(next);
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }

        // System.out.println(Arrays.toString(dist));
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
}