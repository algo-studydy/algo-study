import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> adjlist = new ArrayList<>();
        for(int i=0; i<=n; i++){
            adjlist.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjlist.get(from).add(to);
            adjlist.get(to).add(from);
        } // 입력 끝

        System.out.println(bfs(n,x,y,adjlist));
    }

    private static int bfs(int n, int x, int y, ArrayList<ArrayList<Integer>> adjlist) {
        int count = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        boolean[] visited = new boolean[n+1];
        visited[x] = true;
        while(!q.isEmpty()){
            count++;
            int size = q.size();
            for(int t=0; t<size; t++){
                int cur = q.poll();
                for(int i=0; i<adjlist.get(cur).size(); i++){
                    int next = adjlist.get(cur).get(i);
                    if(next == y) return count;
                    if(!visited[next]){
                        q.add(next);
                        visited[next] = true;
                    }
                }
            }
        }

        return -1;
    }
}
