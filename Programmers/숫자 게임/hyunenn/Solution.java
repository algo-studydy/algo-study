import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);

        int left = 0; int right = 0;
        while(left < A.length && right < B.length) {
            if(A[left] < B[right]) {
                left++; right++;
                answer++;
            } else {
                right++;
            }
        }
        return answer;
    }
}