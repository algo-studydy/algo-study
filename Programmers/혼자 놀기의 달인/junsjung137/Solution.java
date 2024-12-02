import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int maxScore = 0;

        for (int start = 0; start < cards.length; start++) {
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

            if (group.size() >= 2) {
                int score = group.get(0) * group.get(1);
                maxScore = Math.max(maxScore, score);
            }
        }

        return maxScore;
    }
}
