class Solution {
    public int solution(String s) {
        int answer = 1;

        // 모든 시작 위치에서 끝 위치를 기준으로 팰린드롬 검사
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (check(s, i, j)) {
                    answer = Math.max(answer, j - i + 1);
                }
            }
        }
        return answer;
    }

    // 팰린드롬인지 확인하는 함수
    public boolean check(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            // 양쪽 끝에서부터 중앙으로 이동
            start++;
            end--;
        }
        return true;
    }
}