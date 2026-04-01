import java.util.*;

class Solution {
    static class Point {
        int next, type;
        Point(int next, int type) {
            this.next = next;
            this.type = type;
        }
    }
    static int[] arr;
    static int infect;
    static int max;
    static List<Point>[] list;
    static int K, N;
    public int solution(int n, int infection, int[][] edges, int k) {
        arr = new int[k];
        max = Integer.MIN_VALUE;
        K = k; N = n;
        infect = infection;
        list = new ArrayList[n + 1];
        for(int i=1;i<=n;i++) {
            list[i] = new ArrayList<Point>();
        }

        for(int i=0;i<edges.length;i++) {
            int st = edges[i][0];
            int end = edges[i][1];
            int type = edges[i][2] - 1;

            list[st].add(new Point(end, type));
            list[end].add(new Point(st, type));
        }

        dfs(0, -1);
        int answer = 0;
        return max;
    }

    public static void dfs(int cnt, int last) {
        if(cnt == K) {
            max = Math.max(bfs(), max);
            return;
        }

        for(int i=0;i<3;i++) {
            if(i == last) continue;
            arr[cnt] = i;
            dfs(cnt + 1, i);
        }
    }

    public static int bfs() {
        boolean[] v = new boolean[N + 1];
        List<Integer> inf = new ArrayList<>();
        // 초기 감염 처리
        v[infect] = true;
        inf.add(infect);
        for(int k=0;k<K;k++) {

            Queue<Integer> Q = new ArrayDeque<>(inf);

            while(!Q.isEmpty()) {
                int n = Q.poll();
                for(Point p : list[n]) {
                    // 현재 연결된 파이프와 동일하고 아직 감염안됬다면 진행
                    if(p.type == arr[k] && !v[p.next]) {
                        v[p.next] = true;
                        inf.add(p.next);
                        Q.offer(p.next);
                    }
                }
            }
        }

        return inf.size();
    }
}