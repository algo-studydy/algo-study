import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int V;
    static int E;
    static int[] visit;
    static ArrayList<Integer>[] adjList;
    static boolean answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testcase = sc.nextInt();
        for(int i = 0; i < testcase; i++){
            V = sc.nextInt();
            E = sc.nextInt();
            visit = new int[V + 1];
            adjList = new ArrayList[V + 1];
            for(int j = 0 ; j < V + 1 ; j++){
                adjList[j] = new ArrayList<>();
            }
            for(int j = 0; j < E; j++){
                int from = sc.nextInt();
                int to = sc.nextInt();
                adjList[from].add(to);
                adjList[to].add(from);
            }
            answer = true;
            for(int k = 1; k < V + 1; k++){
                if(visit[k] == 0){
                    if (!bfs(k)) {
                        answer = false;
                        break;
                    }
                }
            }
            if(answer){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    private static boolean bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        visit[n] = 1;
        q.add(n);
        while(!q.isEmpty()){
            int v = q.poll();
            for(int i : adjList[v]){
                if(visit[i] == 0){
                    visit[i] = -visit[v];
                    q.add(i);
                }else if(visit[i] == visit[v]){
                    return false;
                }
            }
        }
        return true;
    }
}
