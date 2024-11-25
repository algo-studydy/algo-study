import java.util.*;

class Solution {
    static StringBuilder answer;
    
    public String solution(String p) {
        answer = new StringBuilder();
        
        go(p);
        
        return answer.toString();
    }
    
    static void go(String w) {
        if ("".equals(w)) return;
        
        int point1 = 0;
        int point2 = w.length() - 1;
        int lCount = 0; // '(' 갯수
        int rCount = 0; // ')' 갯수
        Deque<Character> dQ = new ArrayDeque<>();
        
        while (point1 != point2) {
            for (int i = 0; i < 2; i++) {
                char c = w.charAt(point1++);
                updateDQ(dQ, c);
                
                if (checkLeftType(c)) lCount++;
                else rCount++;
            }

            if (dQ.isEmpty()) { // u = 올바른 괄호 문자열
                String u = w.substring(0, point1);
                String v = w.substring(point1, point2 + 1);
                answer.append(u);
                go(v);
                break;
            }

            if (lCount == rCount) { // u = 균형잡힌 괄호 문자열
                String u = w.substring(0, point1);
                String v = w.substring(point1, point2 + 1);
                answer.append("(");
                go(v);
                answer.append(")");
                answer.append(convertedU(u));
                break;
            }
        }
    }
    
    static String convertedU(String u) {
        int size = u.length();
        
        String subU = u.substring(1, size - 1);
        int point = 1;
        StringBuilder sb = new StringBuilder();
        
        while (point != size - 1) {
            char c = u.charAt(point);
            if (checkLeftType(c)) sb.append(")");
            else sb.append("(");

            point++;
        }
        
        return sb.toString();
    }
    
    static boolean checkLeftType(char c) {
        return c == '(';
    }

    static void updateDQ(Deque<Character> dQ, char c1) { // dQ에서 마지막 요소(c2) 와 입력(c1) 일치 비교 -> 일치 = c2 제거 | 불일치 = c1 삽입
        if (dQ.isEmpty()) {
            dQ.add(c1);
            return ;
        }

        char c2 = dQ.pollLast();
        if (c1 == ')' && c1 != c2) {
            if (!dQ.isEmpty()) updateDQ(dQ, dQ.pollLast());
            return;
        }
        dQ.add(c2);
        dQ.add(c1);

        return;
    }
    
}