import java.util.*;
import java.io.*;

public class Main {
    static HashMap<Integer, PriorityQueue<Integer>> hMap;
    static int[] visited;
    static int num;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        hMap = new HashMap<>();
        hMap.put(0, new PriorityQueue<>());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        visited = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            hMap.put(i, new PriorityQueue<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            hMap.get(u).add(v);
            hMap.get(v).add(u);
        }
        visited[r] = ++num;
        dfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void dfs(int node) {
        PriorityQueue<Integer> pQueue = hMap.get(node);

        while (!pQueue.isEmpty()) {
            int nextNode = pQueue.poll();
            if (visited[nextNode] == 0) {
                visited[nextNode] = ++num;

                dfs(nextNode);
            }
        }
    }
}