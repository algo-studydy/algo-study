import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < number.length(); i++){
            int num = number.charAt(i) - '0';

            // 스택의 최상단 값이 현재 값보다 작으면 제거
            while(!stack.isEmpty() && k > 0 && stack.peek() < num){
                stack.pop();
                k--;
            }
            stack.push(num);
        }

        StringBuilder sb = new StringBuilder();
        // 아직 제거할 숫자가 남았으면 뒤에서부터 제거
        if(k > 0) {
            for(int i = 0; i < stack.size() - k; i++){
                sb.append(stack.get(i));
            }
        }
        else {
            for(int i = 0; i < stack.size(); i++){
                sb.append(stack.get(i));
            }
        }

        return sb.toString();
    }
}

