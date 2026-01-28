import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int N = sequence.length;

        int start = 0;
        int end = N-1;

        int left = 0;
        int right = 0;
        int sum = 0;
        while(right<N){
            sum += sequence[right];
            while(sum>k) sum-=sequence[left++];

            if(sum == k){
                if(end-start > right - left){
                    start = left;
                    end = right;
                }
            }

            right++;
        }

        int[] answer = {start, end};
        return answer;
    }
}