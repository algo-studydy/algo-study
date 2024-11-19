import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int max = 1; // 이분 탐색에서 탐색 범위의 최소값
        int min = 100000; // 이분 탐색에서 탐색 범위의 최대값

        // 이분 탐색 수행
        while (max <= min) {
            int level = (max + min) / 2; // 중간 값을 level로 설정
            long mid = func(diffs, times, level); // 해당 level에서의 시간 계산
            if (mid > limit)
                max = level + 1; // 시간이 제한을 초과하면 level을 높임
            else
                min = level - 1; // 시간이 제한 이내라면 level을 낮춤
        }
        return max;
    }

    // 특정 level에서 소요 시간을 계산하는 함수
    public long func(int[] diffs, int[] times, int level) {
        long time = 0; // 총 소요 시간
        for (int i = 0; i < diffs.length; i++) {
            // 난이도가 level 이하라면 문제를 바로 해결
            if (diffs[i] <= level) time += times[i];
                // 난이도가 level 초과일 경우, 추가 시간 소요
            else time += (long)(times[i] + times[i - 1]) * (long)(diffs[i] - level) + times[i];
        }
        return time; // 총 소요 시간 반환
    }
}
