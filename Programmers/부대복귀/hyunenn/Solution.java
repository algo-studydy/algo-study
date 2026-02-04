import java.util.*;

class Solution {
    static class Point {
        int next, time;
        Point(int next, int time) {
            this.next = next;
            this.time = time;
        }
    }
    static List<Integer>[] list;
    static Queue<Point> Q;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer> ans = new ArrayList<>();
        list = new List[n+1];
        for(int i=1;i<=n;i++) {
            list[i] = new ArrayList<>();
        }
        // [i]가 갈 수 있는 길 리스트 저장
        for(int i=0;i<roads.length;i++) {
            int f = roads[i][0];
            int s = roads[i][1];
            list[f].add(s);
            list[s].add(f);
        }
        // dest에서 각 위치별로 거리값 갱신
        Queue<Point> Q = new ArrayDeque<>();
        Q.offer(new Point(destination, 0));

        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[destination] = 0;

        while(!Q.isEmpty()) {
            Point p = Q.poll();
            int curr = p.next;

            for(int c : list[curr]) {
                if(dist[c] > p.time + 1) {
                    dist[c] = p.time + 1;
                    Q.offer(new Point(c, p.time + 1));
                }
            }
        }
        int[] answer = new int[sources.length];
        for(int i=0;i<answer.length;i++) {
            if(dist[sources[i]] == Integer.MAX_VALUE) answer[i] = -1;
            else answer[i] = dist[sources[i]];
        }
        return answer;
    }
}