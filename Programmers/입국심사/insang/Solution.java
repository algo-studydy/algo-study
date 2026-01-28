import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);  // 가장 오래 걸리는 심사관을 찾기 위해 오름차순 정렬

        long left = 1;
        // 가장 오래 걸리는 경우는 가장 느린 심사관이 혼자 다 하는 경우
        long right = (long) times[times.length-1] * n;
        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0; // mid 시간 동안 심사할 수 있는 총 인원 수

            // 각 심사관이 mid 시간 동안 심사할 수 있는 인원 계산
            for (int time : times) {
                cnt += mid / time;

                // 이미 n명을 넘었다면 더 이상 계산할 필요 없음
                if (cnt >= n) break;
            }

            // n명 이상 -> right를 줄여 더 짧은 시간을 찾음
            if (cnt >= n) {
                answer = mid;
                right = mid - 1;
            }
            // n명 미만 -> n명 모두 심사 가능하도록 left를 늘림
            else {
                left = mid + 1;
            }
        }

        return answer;
    }
}