class Solution {
    public int solution(String s) {
        int answer = s.length();

        for (int len = 1; len <= s.length() / 2; len++) {
            StringBuilder b = new StringBuilder();
            String prev = s.substring(0, len); // 첫 구간 초기화
            int count = 1;

            for (int j = len; j < s.length(); j += len) {
                String sub = j + len > s.length() ? s.substring(j) : s.substring(j, j + len);

                if (prev.equals(sub)) {
                    //이전에 자른것과 동일하면 카운트 증가
                    count++;
                } else {
                    if (count > 1) {
                        b.append(count);
                    }
                    b.append(prev);

                    prev = sub;
                    count = 1;
                }
            }

            if (count > 1){
                b.append(count);
            }
            b.append(prev);
            //최소값 찾기
            answer = Math.min(answer, b.length());
        }
        return answer;
    }
}