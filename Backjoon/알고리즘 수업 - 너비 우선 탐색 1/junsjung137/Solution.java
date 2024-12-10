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
            hMap.put(i, new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            }));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            hMap.get(u).add(v);
            hMap.get(v).add(u);
        }
        bfs(r);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    static void bfs(int node) {
        Deque<Integer> dQ = new ArrayDeque<>();
        dQ.push(node);
        visited[node] = ++num;

        while (!dQ.isEmpty()) {
            int currNode = dQ.poll();
            PriorityQueue<Integer> pQ = hMap.get(currNode);

            while (!pQ.isEmpty()) {
                int nextNode = pQ.poll();

                if (visited[nextNode] == 0) {
                    visited[nextNode] = ++num;
                    dQ.add(nextNode);
                }
            }
        }
    }
}