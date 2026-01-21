import java.util.*;

class Solution {
    static class Point {
        String next; int idx;
        Point(String next, int idx) {
            this.next = next;
            this.idx = idx;
        }
    }
    static HashMap<String, List<Point>> map = new HashMap<>();
    static boolean[] v;
    static boolean found = false;
    static int N;
    static List<String> ans = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        N = tickets.length;
        v = new boolean[N];
        for(int i=0;i<N;i++) {
            String st = tickets[i][0];
            String ed = tickets[i][1];

            List<Point> list = map.getOrDefault(st, new ArrayList<>());
            list.add(new Point(ed, i));
            map.put(st, list);
        }

        for(List<Point> ls : map.values()) {
            ls.sort((a, b) -> a.next.compareTo(b.next));
        }

        ans.add("ICN");
        dfs("ICN", 0);

        String[] answer = new String[ans.size()];
        int idx = 0;
        for(String s : ans) {
            answer[idx++] = s;
        }

        return answer;
    }

    private static void dfs(String idx, int depth) {
        if(found) return;

        if(depth == N) {
            found = true;
            return;
        }

        List<Point> ls = map.get(idx);

        if(ls == null) return;

        for(Point p : ls) {
            if(v[p.idx]) continue;

            v[p.idx] = true;
            ans.add(p.next);
            dfs(p.next, depth + 1);

            if(found) return;

            v[p.idx] = false;
            ans.remove(ans.size() - 1);
        }
    }
}