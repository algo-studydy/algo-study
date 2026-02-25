import java.util.*;

class Solution {
    static Queue<Integer> Q1, Q2;
    static long sum1, sum2;
    static int size1, size2;
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        sum1 = 0; sum2 = 0;
        Q1 = new ArrayDeque<>();
        Q2 = new ArrayDeque<>();
        for(int i=0;i<queue1.length;i++) {
            Q1.offer(queue1[i]);
            sum1 += queue1[i];
        }
        for(int i=0;i<queue2.length;i++) {
            Q2.offer(queue2[i]);
            sum2 += queue2[i];
        }
        // System.out.println(sum1 + " " + sum2);
        size1 = Q1.size(); size2 = Q2.size();

        // 두 큐중 큰걸 우선적으로 뺀다
        // 반복조건은 두 큐의 합이 같아질때까지
        // 되돌아왔다면 -1로 종료할건데, 되돌아온 기준은?
        // 초기 sum에 대한 값으로 비교한다면?
        while(sum1 != sum2) {
            if(sum1 > sum2) {
                int a = Q1.poll();
                sum1 -= a;
                Q2.offer(a);
                sum2 += a;
            } else if(sum1 < sum2){
                int b = Q2.poll();
                sum2 -= b;
                Q1.offer(b);
                sum1 += b;
            }

            answer++;

            if(size1 * 3 < answer ||
                    size2 * 3 < answer) {
                answer = -1;
                break;
            }
        }
        return answer;
    }
}