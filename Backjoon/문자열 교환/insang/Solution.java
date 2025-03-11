import java.util.Scanner;

public class BOJ1522문자열교환 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int leng = s.length();

        int aCnt = 0;
        // 문자 a의 개수를 카운트
        for(char c : s.toCharArray()){
            if(c == 'a') aCnt++;
        }

        // 원형의 문자열을 쉽게 처리하기 위해 뒤에 이어줌
        s += s;

        int bCnt = 0;
        // 첫 번째 윈도우에서의 b 개수를 카운트
        for(int i = 0; i < aCnt; i++){
            if(s.charAt(i) == 'b') bCnt++;
        }

        int min = bCnt;  // 초기의 최소 개수는 첫 번째 윈도우의 b 개수
        // 탐색 범위 : a의 개수 ~ 문자열 길이 + a의 개수(원형의 문자열 처리와 모든 경우 탐색)
        for(int i = aCnt; i < leng + aCnt; i++){
            if(s.charAt(i) == 'b') bCnt++;  // 다음 윈도우의 끝이 b라면 bCnt 증가

            if(s.charAt(i - aCnt) == 'b') bCnt--;  // 이전 윈도우의 시작이 b라면 bCnt 감소

            min = Math.min(min, bCnt);  // 모든 윈도우에서 최소값 갱신
        }

        System.out.println(min);
    }
}
