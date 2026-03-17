import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";

        Stack<Character> stack = new Stack<>();

        for(char c : number.toCharArray()){
            // 스택의 최상단이 현재 숫자보다 작고 아직 제거할 수 있다면 최상단 제거
            while(!stack.isEmpty() && stack.peek()<c && k>0){
                stack.pop();
                k--;
            }

            stack.push(c);
        }

        while(k-- > 0) stack.pop();

        StringBuilder sb = new StringBuilder();
        for(char c : stack){
            sb.append(c);
        }
        return sb.toString();
    }
}