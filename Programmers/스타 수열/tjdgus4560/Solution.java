import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if (n < 2) return 0;

        int[] count = new int[n];
        List<Integer>[] positions = new ArrayList[n];

        for (int i=0; i<n; i++) {
            int v = a[i];
            count[v]++;
            if (positions[v] == null) {
                positions[v] = new ArrayList<>();
            }
            positions[v].add(i);
        }

        int answer = 0;

        for (int v=0; v<n; v++) {
            if (positions[v] == null) continue;
            if (count[v] * 2 <= answer) continue;

            int pairCount = 0;
            int lastUsed = -1;

            for (int pos : positions[v]) {
                // 현재 포지션 이전(-1)의 값으로 집합 생성하는 경우
                if (pos - 1 > lastUsed && a[pos - 1] != v) {
                    pairCount++;
                    lastUsed = pos;
                    // 현재 포지현 이후(+1)의 값으로 집합 생성하는 경우
                } else if (pos > lastUsed && pos + 1 < n && a[pos + 1] != v) {
                    pairCount++;
                    lastUsed = pos + 1;
                }
            }
            answer = Math.max(answer, pairCount * 2);
        }

        return answer;
    }
}
