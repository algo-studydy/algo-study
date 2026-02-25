import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0, sum2 = 0;
        for (int num : queue1) {
            q1.offer(num);
            sum1 += num;
        }
        for (int num : queue2) {
            q2.offer(num);
            sum2 += num;
        }

        long total = sum1 + sum2;
        // 홀수인경우 무조건안됨
        if (total % 2 != 0) return -1;
        long target = total / 2;

        int n = queue1.length;
        int maxTime = 2 * n;

        int time = 0;
        while (time <= maxTime) {
            if (sum1 == target) return time;

            if (sum1 < target) {
                if (q2.isEmpty()) return -1;
                int tmp = q2.poll();
                sum2 -= tmp;
                sum1 += tmp;
                q1.offer(tmp);
            } else {
                if (q1.isEmpty()) return -1;
                int tmp = q1.poll();
                sum1 -= tmp;
                sum2 += tmp;
                q2.offer(tmp);
            }
            time++;
        }
        return -1;
    }
}