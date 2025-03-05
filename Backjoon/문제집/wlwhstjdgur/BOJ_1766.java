package Study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1766 {
	static int N;   // 노드 갯수
    static int M;   // 간선 갯수
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        int[] indegree = new int[N+1];
 
        for(int i=0; i<N+1; i++)
            list.add(new ArrayList<Integer>());
 
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
 
            // v1 -> v2
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
 
            list.get(v1).add(v2);
            indegree[v2]++;
        }
 
        topologicalSort(indegree, list);        
    }
 
    // 쉬운 문제를 먼저 풀기 위해서 우선순위 큐에 담음
    static void topologicalSort(int[] indegree, List<List<Integer>> list) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
 
        for(int i=1; i<=N; i++) {
            if(indegree[i] == 0)
                pq.offer(i);
        }
 
        while(!pq.isEmpty()) {
            int node = pq.poll();
 
            for(Integer i : list.get(node)) {
                indegree[i]--;
 
                if(indegree[i] == 0)
                    pq.offer(i);
            }
 
            System.out.print(node + " ");
        }
    }
}
