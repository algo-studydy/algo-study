import java.util.*;

class Solution {
    public int solution(int[] cards) {
        boolean[] visited = new boolean[cards.length];
        ArrayList<Integer> group = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                int groupCount = 0;
                int current = i;

                while (!visited[current]) {
                    visited[current] = true;
                    groupCount++;
                    current = cards[current] - 1;
                }
                group.add(groupCount);
            }
        }

        Collections.sort(group, Collections.reverseOrder());

        if (group.size() < 2) {
            return 0;
        } else {
            return group.get(0) * group.get(1);
        }
    }
}
