import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        // 내림차순 우선순위 큐
        PriorityQueue<Integer> pqA = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> pqB = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < A.length; i++) {
            pqA.offer(A[i]);
            pqB.offer(B[i]);
        }

        // A팀의 가장 큰 카드부터 차례로 확인
        while (!pqA.isEmpty()) {
            int aMax = pqA.poll();
            int bMax = pqB.peek();  // B팀의 가장 큰 카드

            if (bMax > aMax) {
                answer++;
                pqB.poll(); // 사용한 카드를 큐에서 제거
            }
        }

        return answer;
    }
}