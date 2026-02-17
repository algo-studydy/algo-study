import java.util.*;

class Solution {
    static int n, answer;
    static boolean[] v;
    static List<Integer> list = new ArrayList<>();
    public int solution(int n) {
        answer = 0;
        v = new boolean[n];
        dfs(0, n);
        return answer;
    }

    private static void dfs(int idx, int n) {
        // basis
        if(idx == n) {
            answer++;
            // System.out.println(list);
            return;
        }
        // inductive
        for(int i=0;i<n;i++) {
            if(v[i]) continue;

            if(possible(idx, i)) {
                v[i] = true;
                list.add(i);
                dfs(idx + 1, n);
                list.remove(list.size() - 1);
                v[i] = false;
            }
        }
    }

    private static boolean possible(int r, int c) {
        for(int row = 0;row < list.size(); row++) {
            int col = list.get(row);
            if(Math.abs(r - row) == Math.abs(c - col)) return false;
        }
        return true;
    }
}