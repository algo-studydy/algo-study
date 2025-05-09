import java.util.*;

public class BOJ6236용돈관리 {
    static int[] money;
    static int m;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        m = sc.nextInt();

        money = new int[n];  // 하루마다 필요한 금액
        int max = 0;  // n일 중 최대 금액
        int sum = 0;  // 전체 금액 합계

        for (int i = 0; i < n; i++) {
            money[i] = sc.nextInt();
            max = Math.max(max, money[i]);  // 최소 출금 금액의 하한선
            sum += money[i];                // 최대 출금 금액의 상한선
        }

        int left = max;  // 최소 출금 금액 (가장 큰 금액)
        int right = sum;  // 최대 출금 금액 (모든 날 합계, 최대 출금 금액 <= 금액의 합)
        int answer = sum;

        while (left <= right) {
            int mid = (left + right) / 2;

            // mid 금액을 m번 이하로 출금 가능한지 검사
            if (check(mid)) {
                answer = mid;  // mid로 가능하면 정답 갱신
                right = mid - 1;  // 금액을 줄여도 가능한지 확인
            }
            else left = mid + 1;  // mid로 불가능하면 금액 증가
        }

        System.out.println(answer);
    }

    // mid 금액을 m번 이하로 출금 가능한지 검사
    static boolean check(int mid) {
        int cnt = 1;  // 출금 횟수
        int cur = mid;  // 현재 사용 가능한 금액

        for (int i = 0; i < money.length; i++) {
            // 현재 잔액이 i번째 날 사용할 금액보다 낮으면
            if (cur < money[i]) {
                cur = mid;  // 새로 출금
                cnt++;  // 출금 횟수 증가
            }
            cur -= money[i];  // 오늘 쓴 금액 차감
        }

        return cnt <= m;
    }
}