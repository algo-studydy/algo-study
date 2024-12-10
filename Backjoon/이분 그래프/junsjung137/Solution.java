import java.util.*;
import java.io.*;

public class Main {
    static HashMap<Integer, Deque<Integer>> hMap;
    static int[] group;

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for (int t = 0; t < tc; t++) {
            hMap = new HashMap<>();
            hMap.put(0, new ArrayDeque<>());
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int r = 1;
            group = new int[v + 1];

            for (int i = 1; i <= v; i++) {
                hMap.put(i, new ArrayDeque<>());
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                r = node1;
                hMap.get(node1).add(node2);
                hMap.get(node2).add(node1);
            }
            String ans = dfs(r, 1) ? "NO" : "YES";
            if ("YES".equals(ans)) {
                for (int i = 1; i <= v; i++) {
                    if (group[i] == 0) {
                        ans = dfs(i, 1) ? "NO" : "YES";
                    }
                }
            }
            System.out.println(ans);
        }
    }

    private static boolean dfs(int vertex, int groupID) {
        group[vertex] = groupID;
        int newGroupID = groupID == 1 ? 2 : 1;
        Deque<Integer> stack = hMap.get(vertex);

        while (!stack.isEmpty()) {
            int node = stack.poll();

            if (group[node] != 0) {
                if (group[node] == groupID) {
                    return true;
                }
                continue;
            }

            group[node] = newGroupID;
            boolean isDOne = dfs(node, newGroupID);
            if (isDOne) {
                return true;
            }
        }

        return false;
    }
}