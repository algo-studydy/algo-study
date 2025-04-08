import java.util.*;

public class Main {
    static class Node{
        int v; //정점
        int e; //가중치

        public Node(int v, int e) {
            this.v = v;
            this.e = e;
        }
    }

    static ArrayList<ArrayList<Node>> adjlist;
    static int N,M;
    static int start, end;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        adjlist = new ArrayList<>();
        for(int i=0; i<=N; i++){
            adjlist.add(new ArrayList<>());
        }

        int maxCost = 0; //이분탐색시 탐색할 최대 중량

        for(int i=0; i<M; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            int e = sc.nextInt();

            adjlist.get(from).add(new Node(to, e));
            adjlist.get(to).add(new Node(from, e));
            maxCost = Math.max(maxCost, e);
        }

        start = sc.nextInt();
        end = sc.nextInt();
        // 입력끝

        int left = 1;
        int right = maxCost;
        int ans = 0;

        // 이분탐색으로 mid 중량만큼 옮길 수 있는지 탐색
        while(left <= right){
            int mid = (left + right) / 2;
            visited = new boolean[N+1];

            if(dfs(start, mid)){
                ans = mid;
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }


        System.out.println(ans);
    }

    // 최소 limit 만큼 옮길 수 있어야 dfs 동작
    private static boolean dfs(int start, int limit) {
        if(start == end) return true;

        visited[start] = true;
        for( Node node : adjlist.get(start)){
            if(!visited[node.v] && limit <= node.e){
                if(dfs(node.v, limit)){
                    return true;
                }
            }
        }
        return false;
    }
}
