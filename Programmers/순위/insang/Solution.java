import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        Map<Integer, Set<Integer>> win = new HashMap<>();  // 선수가 이긴 사람들 목록
        Map<Integer, Set<Integer>> lose = new HashMap<>();  // 선수를 이긴 사람들 목록

        for (int i = 1; i <= n; i++) {
            win.put(i, new HashSet<>());
            lose.put(i, new HashSet<>());
        }

        for (int[] res : results) {
            int winner = res[0];
            int loser = res[1];
            win.get(winner).add(loser);
            lose.get(loser).add(winner);
        }

        for (int i = 1; i <= n; i++) {
            // i에게 진 사람들(loser)은 i를 이긴 사람들(winner)한테도 짐
            for (int loser : win.get(i)) {
                for (int winner : lose.get(i)) {
                    win.get(winner).add(loser);
                    lose.get(loser).add(winner);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            // 나를 이긴 사람 + 내가 이긴 사람 == 전체 인원-1
            if (win.get(i).size() + lose.get(i).size() == n-1) {
                answer++;
            }
        }

        return answer;
    }
}