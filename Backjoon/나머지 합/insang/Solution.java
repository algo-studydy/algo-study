import java.util.Scanner;

public class BOJ10986나머지합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 배열 크기
        int m = sc.nextInt(); // 나눌 값
        long[] remainderCount = new long[m]; // 나머지의 빈도 저장
        long sum = 0; // 누적 합
        long answer = 0; // 정답

        // 배열 입력 및 누적 합 처리
        for (int i = 0; i < n; i++) {
            sum += sc.nextInt(); // 누적 합 갱신
            int remainder = (int)(sum % m); // 나머지 계산
//            if (remainder < 0) remainder += m; // 음수 나머지 보정

            // 나머지가 0인 경우 즉시 정답 증가
            if (remainder == 0) answer++;

            // 이전에 같은 나머지가 나온 횟수만큼 더하기
            answer += remainderCount[remainder];
            remainderCount[remainder]++;
        }

        System.out.println(answer);
    }
}
