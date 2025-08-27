import java.util.*;

public class Main {

    static int N;
    static ArrayList<ArrayList<Integer>> adjlist;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 유저의 수
        int M = sc.nextInt(); // 친구 관계 수

        adjlist = new ArrayList<>();
        for(int i=0; i<=N; i++){
            adjlist.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            if(adjlist.get(from).contains(to)) continue;
            adjlist.get(from).add(to);
            adjlist.get(to).add(from);
        }

        int[] arr = new int[N+1];
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j) continue;
                arr[i] += bfs(i,j);
            }
        }

        int ans =1;
        for(int i=2; i<=N; i++){
            if(arr[ans] > arr[i]){
                ans = i;
            }
        }

        System.out.println(ans);
    }

    private static int bfs(int from, int to) {
        boolean[] v = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        for(int n : adjlist.get(from)){
            q.add(n);
            v[n] = true;
        }

        int count =0;
        while(!q.isEmpty()){
            int size = q.size();
            count++;
            for(int t=0; t<size; t++){
                int n = q.poll();
                if(n == to){
                    return count;
                }

                for(int num : adjlist.get(n)){
                    if(!v[num]){
                        q.add(num);
                        v[num] = true;
                    }
                }
            }
        }

        return -1;
    }
}
