class Solution {
    static long time;
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;
        long time = 0;
        for(int i=0;i<times.length;i++) {
            time = Math.max(time, times[i]);
        }

        long left = 0, right = time * n;
        while(left < right) {
            long mid = (left + right) / 2;
            if(solve(n, times, mid)) {
                answer = Math.min(answer, mid);
                right = mid;
            }
            else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private static boolean solve(int n, int[] times, long mid) {
        long sum = 0;
        for(int i=0;i<times.length;i++) {
            sum += mid / times[i];
        }

        return sum >= n;
    }
}
// 7, 10 이면, 30/7 + 30/10 = 4+3 =7