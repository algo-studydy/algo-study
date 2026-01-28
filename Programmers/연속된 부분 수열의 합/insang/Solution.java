import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int[] prefix = new int[sequence.length+1];

        for(int i = 1; i <= sequence.length; i++){
            prefix[i] = prefix[i-1] + sequence[i-1];
        }

        int left = 0;
        int right = 1;
        int length = Integer.MAX_VALUE;
        while(left <= right && right <= sequence.length){
            int sum = prefix[right] - prefix[left];

            if(sum < k) right++;  // 구간합이 k보다 작으면 right를 증가해 합을 키움
            else if(sum > k) left++;  // 구간합이 k보다 크면 left를 감소해 합을 줄임
            else {  // 구간합 == k 일 경우
                if(right - left < length){  // 구간의 길이 체크
                    answer[0] = left;
                    answer[1] = right-1;
                    length = right - left;
                }
                left++;  // 구간의 길이를 줄여 정답 찾기
            }

        }
        return answer;
    }
}