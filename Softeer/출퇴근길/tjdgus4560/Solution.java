import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,S,T;
    static List<Integer>[] adjlist;
    static List<Integer>[] adjlistR;
    static boolean[] fromS;
    static boolean[] fromT;
    static boolean[] toS;
    static boolean[] toT;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjlist = new ArrayList[N+1];
        adjlistR = new ArrayList[N+1];
        for(int i=0; i<N+1; i++){
            adjlist[i] = new ArrayList<>();
            adjlistR[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjlist[from].add(to);
            adjlistR[to].add(from);
        }
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        fromS = new boolean[N+1];
        fromT = new boolean[N+1];
        toT = new boolean[N+1];
        toS = new boolean[N+1];
        fromS[T] = true;
        dfs(S, adjlist, fromS);
        fromT[S] = true;
        dfs(T, adjlist, fromT);
        dfs(S, adjlistR, toS);
        dfs(T, adjlistR, toT);
        int ans = 0;
        for(int i=1; i<=N; i++){
            if(fromS[i] && fromT[i] && toT[i]&& toS[i]) ans++;
        }
        System.out.println(ans-2);
    }

    private static void dfs(int cur, List<Integer>[] adjlist, boolean[] visited) {
        if(visited[cur]){
            return;
        }

        visited[cur] = true;
        for(Integer next: adjlist[cur]){
            dfs(next, adjlist, visited);
        }
    }


}