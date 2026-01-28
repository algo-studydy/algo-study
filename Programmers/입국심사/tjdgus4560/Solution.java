import java.util.*;

class Solution {
    int[] times;
    int n;

    public long solution(int n, int[] times) {
        this.n = n;
        this.times = times;

        long answer = bs();
        return answer;
    }

    public long bs(){
        long left = 1;
        long right = Long.MAX_VALUE;
        long answer = right;

        while(left <= right) {
            //오버플로우 방지 mid 계산
            long mid = left+(right-left) / 2;

            if(isVaild(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // mid 시간만큼 모두 확인가능한지 체크
    private boolean isVaild(long mid) {
        long count = 0;

        for(int t : times) {
            count += mid / t;

            // 오버플로우 방지
            if(count >= n) return true;
        }

        return count >= n;
    }
}