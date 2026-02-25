import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int a = 0;
        int b = 0;
        int score = 0;

        while (a<A.length && b<B.length) {
            if (B[b] > A[a]) {
                // 이길 수 있으면 현재가 최적해
                score++;
                a++;
                b++;
            } else {
                b++;
            }
        }
        return score;
    }
}