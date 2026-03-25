import java.util.*;

class Solution {
    public int[] solution(int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] answer = new int[2];

        for (int i = 1; i <= 20; i++) map.put(i * 3, 0);
        for (int i = 1; i <= 20; i++) map.put(i * 2, 0);
        for (int i = 1; i <= 20; i++) map.put(i, 1);
        map.put(50, 1);

        int[] dpCnt = new int[target + 1];
        int[] dpSingle = new int[target + 1];

        Arrays.fill(dpCnt, Integer.MAX_VALUE);
        Arrays.fill(dpSingle, -1);

        dpCnt[0] = 0;
        dpSingle[0] = 0;

        for (int i = 1; i <= target; i++) {
            // 던질 수 있는 점수를 모두 순회
            for (int score : map.keySet()) {
                if (i - score < 0) continue;
                if (dpCnt[i - score] == Integer.MAX_VALUE) continue;

                int nextCnt = dpCnt[i - score] + 1;
                int nextSingle = dpSingle[i - score] + map.get(score);

                // 카운트가 적은게 우선, 카운트가 같다면 싱글불이 큰게 우선
                if (nextCnt < dpCnt[i]) {
                    dpCnt[i] = nextCnt;
                    dpSingle[i] = nextSingle;
                } else if (nextCnt == dpCnt[i] && nextSingle > dpSingle[i]) {
                    // 싱글이나 불을 더 많이 던질수 있는경우 갱신
                    dpSingle[i] = nextSingle;
                }
            }
        }

        answer[0] = dpCnt[target];
        answer[1] = dpSingle[target];
        return answer;
    }
}