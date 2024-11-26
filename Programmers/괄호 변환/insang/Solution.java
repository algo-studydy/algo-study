class Solution {
    public String solution(String p) {
        if(p.equals("")) return "";

        int flag = 0;
        int splitIndex = 0;

        // 문자열을 u, v로 분리
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                flag++;
            } else {
                flag--;
            }
            if (flag == 0) {
                splitIndex = i + 1; // 균형잡힌 문자열의 끝 인덱스
                break;
            }
        }

        String u = p.substring(0, splitIndex);
        String v = p.substring(splitIndex);
        // System.out.println(u + " " + v);

        // u가 올바른 괄호 문자열인지 확인
        if (isVaild(u)) {
            // 올바른 경우 v에 대해 재귀적으로 수행한 결과를 이어붙임
            return u + solution(v);
        } else {
            // 올바르지 않은 경우 새로운 문자열 생성
            StringBuilder result = new StringBuilder();
            result.append("(");
            result.append(solution(v)); // v를 재귀적으로 변환
            result.append(")");

            // u의 첫 번째와 마지막 문자를 제거하고 나머지 괄호 방향을 뒤집음
            u = u.substring(1, u.length() - 1);
            for (char c : u.toCharArray()) {
                if (c == '(') {
                    result.append(")");
                } else {
                    result.append("(");
                }
            }
            return result.toString();
        }
    }

    // 올바른 괄호 문자열 검사 함수
    public boolean isVaild(String s) {
        int flag = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                flag++;
            } else {
                flag--;
            }
            if (flag < 0) { // ')'가 먼저 나온 경우
                return false;
            }
        }
        return flag == 0; // 모든 괄호가 짝을 이룰 때만 true
    }
}