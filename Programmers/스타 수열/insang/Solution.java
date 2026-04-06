import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if (n < 2) return 0;

        // 각 원소의 등장 횟수 세기
        Map<Integer, Integer> count = new HashMap<>();
        for (int v : a) {
            count.put(v, count.getOrDefault(v, 0) + 1);
        }

        int ans = 0;

        // 각 원소를 공통 원소 후보로 잡고 스타 수열 길이 계산
        for (int target : count.keySet()) {
            // 이 원소로 만들 수 있는 최대 길이가 현재 답보다 작으면 스킵
            if (count.get(target) * 2 <= ans) continue;

            int group = 0;
            int i = 0;

            // 배열을 순회하며 target을 포함하는 쌍을 그리디하게 만들기
            while (i < n-1) {
                // 인접한 두 원소 중 하나가 target이고, 둘이 서로 다르면 쌍 성립
                if ((a[i] == target || a[i + 1] == target) && a[i] != a[i + 1]) {
                    group++;
                    i += 2; // 쌍으로 묶었으니 두 칸 건너뛰기
                }
                else i++;
            }

            ans = Math.max(ans, group * 2);
        }

        return ans;
    }
}