import java.util.*;

class Solution {
    static int n;
    static List<int[]> nodes;
    static long limit;

    public int solution(int[] diffs, int[] times, long limit) {
        int answer = -1;
        n = diffs.length;
        Solution.limit = limit;
        
        nodes = new ArrayList<>();  // 0 = diff, 1 = time
        nodes.add(new int[] {diffs[0], times[0], 0});
        Solution.limit -= times[0];
        int maxLevel = diffs[0];
        for (int i = 1; i < n; i++) {
            Solution.limit -= times[i];
            maxLevel = Math.max(maxLevel, diffs[i]);
            nodes.add(new int[] {diffs[i], times[i], times[i - 1]});
        }
        Collections.sort(nodes, (n1, n2) -> {
            return n1[0] - n2[0];
        });
        /*
        for (int[] n : nodes) {
            System.out.print(n[0] + " ");
        }
        */
        /* 
        System.out.println(maxLevel);
        */
        
        int r = maxLevel;
        int l = 0;
        while (r - l > 1) {
            int v = (r + l) / 2;
            
            if (simulation(v)) {
                r = v;
                continue;
            }
            
            l = v;
        }
        
        answer = r;
        return answer;
    }
    
    static boolean simulation(int level) {
        long sum = 0;
        int startIdx = findBT(level);
        
        for (int i = startIdx; i < n; i++) {
            sum += (nodes.get(i)[0] - level) * (nodes.get(i)[1] + nodes.get(i)[2]);
            if (sum > limit) return false;
        }
        
        return true;
    }
    
    static int findBT(int value) {
        int l = 0;
        int r = n;
        
        if (nodes.get(l)[0] > value) return l;
        
        while (r - l > 1) {
            int v = (r + l) / 2;
            
            if (nodes.get(v)[0] > value) {
                r = v;
                continue;
            }
            
            l = v;
        }

        return nodes.get(l)[0] < value ? r : l;
    }
}