import java.util.*;

class Solution {
    public String solution(String p) {
        if(checkGood(p)) return p;
        return recur(p);
    }

    String recur(String p){
        StringBuilder sb = new StringBuilder();
        int idx = 1;
        while(idx<=p.length()){
            String u = p.substring(0, idx);
            String v = p.substring(idx, p.length());
            if(!checkBal(u)){
                idx++;
            }else if(checkGood(u)){
                sb.append(u);
                sb.append(recur(v));
                break;
            }else{
                sb.append("(");
                sb.append(recur(v));
                sb.append(")");
                String su = u.substring(1,u.length()-1);
                for(int i = 0; i<su.length(); i++){
                    char c = su.charAt(i);
                    if(c == '(') sb.append(")");
                    else sb.append("(");
                }
                break;
            }
        }
        return sb.toString();
    }

    boolean checkBal(String s){
        int lc = 0;
        int rc = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') lc++;
            else rc++;
        }
        return rc==lc;
    }

    boolean checkGood(String s){
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.add(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }
        return true;
    }
}