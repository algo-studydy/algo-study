import java.util.*;

class Solution {
    Stack<Integer> stack = new Stack<>();
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i=0;i<prices.length;i++) {
            if(stack.isEmpty()) stack.push(i);
                // 만약, 새로 들어오는 값이 기존 값보다 작다면 제거
            else {
                while(!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                    int now = stack.pop();
                    answer[now] = i - now;
                }

                stack.push(i);
            }

            // System.out.println(i + " " + stack.size());
        }

        while(!stack.isEmpty()) {
            int a = stack.pop();
            answer[a] = prices.length - a - 1;
        }
        return answer;
    }
}