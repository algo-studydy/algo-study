import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int idx; //도착지점
        int cost; //비용

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N; // 노드 수
    static int M; // 간선 수
    static int X; // 파티할 노드
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        X = sc.nextInt();

        ArrayList<ArrayList<Node>> adjlist = new ArrayList<>();
        ArrayList<ArrayList<Node>> adjlistR = new ArrayList<>();
        for(int i=0; i<=N; i++){
            adjlist.add(new ArrayList<>());
            adjlistR.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            adjlist.get(from).add(new Node(to, cost));
            adjlistR.get(to).add(new Node(from, cost));
        } // 입력끝

        int[] dist = dijkstra(adjlist, X);
        int[] distR = dijkstra(adjlistR, X);

        int max=0;
        for(int i=0; i<=N; i++){
            if(dist[i] == Integer.MAX_VALUE || distR[i] == Integer.MAX_VALUE){
                continue;
            }
            max = Math.max(max, dist[i]+distR[i]);
        }

        System.out.println(max);
    }

    private static int[] dijkstra(ArrayList<ArrayList<Node>> adjlist, int x) {
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[X] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(x, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            int curIdx = cur.idx;
            int curCost = cur.cost;

            if(curCost > dist[curIdx]) continue;
            for(Node next : adjlist.get(curIdx)){
                int nextIdx = next.idx;
                int newCost = curCost + next.cost;

                if(newCost < dist[nextIdx]){
                    dist[nextIdx] = newCost;
                    pq.offer(new Node(nextIdx, newCost));
                }
            }
        }

        return dist;
    }
}
