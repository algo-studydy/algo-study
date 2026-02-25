import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        long s1 = 0;
        long s2 = 0;

        for (int i = 0; i < queue1.length; i++) {
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
            s1 += queue1[i];
            s2 += queue2[i];
        }

        int answer = 0;
        int maxCnt = queue1.length * 4; // 최대 탐색 횟수 설정

        while (s1 != s2) {
            if (answer > maxCnt) return -1;

            if (s1 > s2) {
                int num = q1.poll();
                s1 -= num;
                s2 += num;
                q2.offer(num);
            }

            else {
                int num = q2.poll();
                s2 -= num;
                s1 += num;
                q1.offer(num);
            }
            answer++;
        }

        return answer;
    }
}