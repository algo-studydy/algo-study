import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<n; i++){
            // 스택 안에 있는 값의 크기가 현재 값보다 크다면 떨어진 시간 저장
            while(!stack.isEmpty() && prices[stack.peek()] > prices[i]){
                int idx = stack.pop();
                answer[idx] = i - idx;
            }

            stack.push(i);
        }

        while(!stack.isEmpty()){
            int idx = stack.pop();
            answer[idx] = n - idx - 1;
        }

        return answer;
    }
}