import java.util.*;

class Solution {
    public int solution(int storey) {
        int answer = 0;
        String s = String.valueOf(storey);
        int len = s.length();  // 현재 위치한 층의 자릿수

        boolean flag = false;
        // 뒤에서부터 순회(16층 -> 6, 1순으로 순회)
        for(int i = len-1; i >= 0; i--){
            int n = s.charAt(i) - '0';
            // 이전 자리의 수에서 +버튼을 누른 경우
            if(flag) {
                n++;  // 현재 자리의 수 값 증가
                flag = false;
            }

            if(n == 5){  // 현재 자릿수가 5일 때
                // 다음 자릿수가 5이상이면 5만큼 +버튼 누름
                if(i > 0 && s.charAt(i-1)-'0' >= 5){
                    answer += 5;
                    flag = true;
                }
                // 다음 자릿수가 5미만이면 5만큼 -버튼 누름
                else answer += 5;
            }
            else if(n > 5){
                answer += (10 - n);  // 10-n 만큼 +버튼을 누름
                flag = true;  // 다음 자리수의 값 증가
            }
            else answer += n;  // n 만큼 -버튼을 누름
        }

        if(flag) answer++;  // 마지막 자릿수에서 +버튼을 눌렀다면 -버튼 한번 눌러야 함

        return answer;
    }

    // 555
    // 5 4 4 1
}