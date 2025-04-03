import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static class Node implements Comparable<Node>{
        int u;
        int v;
        int cost;

        public Node(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt(); // 정점 갯수
        int E = sc.nextInt(); // 간선 갯수
        ArrayList<Node> edges = new ArrayList<>(); //간선 정보 저장

        for(int i=0; i<E; i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Node(u,v,cost));
        }

        // union - find 부모배열 자기자신으로 초기화
        parent = new int[V+1];
        for(int i=0; i<=V; i++){
            parent[i] = i;
        }

        System.out.println(kruskal(V, edges));
    }

    // 부모찾는 find 함수
    static int find(int x){
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    //부모동일하게 합치기
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if( rootA == rootB ) return false; //부모동일 사이클발생 false리턴

        parent[rootA] = rootB;
        return true;
    }

    static int kruskal(int V, ArrayList<Node> edges){
        Collections.sort(edges);

        int costSum = 0;
        int count = 0;

        for(Node edge : edges){
            if(union(edge.u, edge.v)){ //사이클이 발생하지 않는경우
                costSum += edge.cost;
                count++;
                if(count == V-1) break;
            }
        }

        return costSum;
    }


}
