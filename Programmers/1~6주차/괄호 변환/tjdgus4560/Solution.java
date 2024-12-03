class Solution {
    public String solution(String p) {
        return re(p);
    }

    private String re(String p){
        // 빈문자열 반환
        if (p.isEmpty()) {
            return "";
        }
        //u와 v 분리
        int idx = findIdx(p);
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        // u가 올바른 문자열인지 확인
        if (isCorrect(u)) {
            return u + re(v);
        } else {
            // 올바른 문자열로 변환
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(re(v));
            sb.append(")");
            sb.append(case4_4(u));
            return sb.toString();
        }
    }

    // 균형잡힌 문자열을 찾는 메소드
    private int findIdx(String s) {
        int count = 0;
        int idx = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                return idx;
            }
            idx++;

        }
        return -1; // 이론상 도달하지 않음
    }

    // 올바른 문자열인지 확인하는 메소드
    private boolean isCorrect(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
                if (count < 0) { // 닫는 괄호가 더 많아지면 올바르지 않음
                    return false;
                }
            }
        }
        return count == 0;
    }

    // 4-4 조건 수행
    private String case4_4(String u) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < u.length() - 1; i++) {
            char c = u.charAt(i);
            if (c == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }
}