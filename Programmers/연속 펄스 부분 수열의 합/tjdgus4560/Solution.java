import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;

        long[] dp1 = new long[n]; //1, -1, 1 ...
        long[] dp2 = new long[n]; // -1, 1, 1 ...

        dp1[0] = sequence[0];
        dp2[0] = -sequence[0];
        long max = Math.max(dp1[0], dp2[0]);

        for(int i=1; i<n; i++){
            dp1[i] = Math.max(dp2[i-1], 0) + sequence[i];
            dp2[i] = Math.max(dp1[i-1], 0) - sequence[i];
            max = Math.max(max, Math.max(dp1[i], dp2[i]));
        }
        return max;
    }
}