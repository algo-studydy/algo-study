import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Vertex implements Comparable<Vertex> {
        int v, w;

        public Vertex(int v, int w) {
            this.v = v;
            this.w = w;
        }

        // 우선순위 큐에 넣어야 하므로 비교연산 필요
        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.w, o.w);
        }
    }

    private static final int INF = Integer.MAX_VALUE;
    static int V, E, Ans;
    static List<Vertex>[] list;
    static int[] dist;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        int str = sc.nextInt();

        // 거리 초기화
        list = new ArrayList[V];
        dist = new int[V];
        for (int i = 0; i < V; i++) {
            list[i] = new ArrayList<>();
        }
        Arrays.fill(dist, INF);

        // 리스트에 그래프 정보를 초기화
        // 출발 도착 가중치
        for (int i = 0; i < E; i++) {
            int s = sc.nextInt()-1;
            int e = sc.nextInt()-1;
            int w = sc.nextInt();
            list[s].add(new Vertex(e, w));
        }

        // 다익스트라 알고리즘
        dijkstra(str-1);
        // 출력 부분
        for (int i = 0; i < V; i++) {
            Ans += dist[i];
        }
        for (int i = 0; i < dist.length; i++) {
            if(dist[i] != Integer.MAX_VALUE) {
                System.out.println(dist[i]);
            }else {
                System.out.println("INF");
            }
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Vertex> Q = new PriorityQueue<>();
        //선택배열
        boolean[] v = new boolean[V];
        //시작노드를 pq에 삽입
        Q.add(new Vertex(start, 0));
        //시작노드까지의 거리는 0
        dist[start] = 0;

        while (!Q.isEmpty()) {
            Vertex p = Q.poll();
            int minIdx = p.v;

            if (v[minIdx] == true)
                continue;
            v[minIdx] = true;

            //모든 정점들에 대해서 탐색
            for (Vertex next : list[minIdx]) {
                //더 가까운 거리로 갱신
                if (dist[next.v] > dist[minIdx] + next.w) {
                    dist[next.v] = dist[minIdx] + next.w;
                    Q.add(new Vertex(next.v, dist[next.v]));
                }
            }
        }
    }
}
