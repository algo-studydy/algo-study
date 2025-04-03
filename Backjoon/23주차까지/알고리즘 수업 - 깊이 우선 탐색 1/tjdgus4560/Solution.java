import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    static int V;
    static int E;
    static int R;
    static boolean[] visited;
    static int[] visit;
    static int visitedTime = 1;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) {
        //입력
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int R = sc.nextInt();

        //인접리스트 생성, 초기화
        adjList = new ArrayList[V + 1];
        for(int i = 0; i < V + 1; i++){
            adjList[i] = new ArrayList<>();
        }


        for(int i = 0 ; i < E ; i++ ){
            int from = sc.nextInt();
            int to = sc.nextInt();

            adjList[from].add(to);
            adjList[to].add(from);
        }
        for(ArrayList<Integer> list : adjList){
            Collections.sort(list);
        }

        //dfs
        visited = new boolean[V + 1];
        visit = new int[V + 1];
        dfs(adjList[R],R);


        for(int i = 1 ; i < V + 1 ; i++){
            System.out.println(visit[i]);
        }


    }

    public static void dfs(ArrayList<Integer> head,int v){
        visited[v] = true;
        visit[v] = visitedTime++;
        for(int i : head){
            if(!visited[i]){
                dfs(adjList[i], i);
            }
        }

    }
}
