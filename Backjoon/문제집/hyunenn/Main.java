import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer>[] list;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=1;i<=M;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list[a].add(b);
        }
        int[] degree = new int[N+1];
        for(int i=1;i<=N;i++) {
            for(int j : list[i]) {
                degree[j]++;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1;i<=N;i++) {
            if(degree[i] == 0)
                pq.add(i);
        }

        List<Integer> answer = new ArrayList<>();
        while(!pq.isEmpty()) {
            int curr = pq.poll();
            answer.add(curr);

            for(int i : list[curr]) {
                degree[i]--;
                if(degree[i] == 0)
                    pq.offer(i);
            }
        }

        for(int i : answer) {
            System.out.print(i + " ");
        }
    }
}