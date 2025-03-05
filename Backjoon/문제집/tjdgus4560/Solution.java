import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        //입력
        ArrayList<ArrayList<Integer>> adjlist = new ArrayList<>();
        int[] inDegree = new int[n+1]; // 위상정렬용
        for(int i=0; i<=n; i++){
            adjlist.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            int from = sc.nextInt();
            int to = sc.nextInt();

            adjlist.get(from).add(to);
            inDegree[to]++;
        }

        //우선순위 큐를 이용한 위상 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=n; i++){
            if(inDegree[i] == 0){
                pq.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur).append(" ");

            // 위상정렬 : 진입차수가 0인 노드부터 시작해서 간선을 제거해가며 새로이 진입차수가 0이 되는 노드를 계속 추가하며 구성
            for(int next : adjlist.get(cur)){
                if(--inDegree[next] == 0){
                    pq.add(next);
                }
            }
        }

        System.out.println(sb);
    }
}
