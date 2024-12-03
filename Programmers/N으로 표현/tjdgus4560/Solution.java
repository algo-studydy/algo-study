import java.util.*;


class Solution {
    public int solution(int N, int number) {
        if (N == number) {
            return 1;
        }

        // 초기화
        List<Set<Integer>> dp = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        // 1번 사용했을 때는 N만 가능
        dp.get(1).add(N);

        // N을 i번 사용하면서 가능한 조합들
        for(int i = 2; i<=8 ;i++){
            // N을 이어붙인 숫자
            int tmp = 0;
            for(int j = 0; j<i ;j++){
                tmp += Math.pow(10, j)*N;
            }
            dp.get(i).add(tmp);

            // i-1까지 사용가능한 조합들을 이용한 계산 추가
            for (int j = 1; j < i/2+1; j++) {
                // N을 총i 번 사용하는 조합을 계산하기 위해 j, i-j dp 의 원소를 이용
                for (int num1 : dp.get(j)) {
                    for (int num2 : dp.get(i - j)) {
                        dp.get(i).add(num1 + num2); // 덧셈
                        dp.get(i).add(num1 - num2); // 뺄셈
                        dp.get(i).add(num2 - num1); // 뺄셈
                        dp.get(i).add(num1 * num2); // 곱셈
                        if (num2 != 0) {
                            dp.get(i).add(num1 / num2); // 나눗셈
                        }
                        if (num1 != 0) {
                            dp.get(i).add(num2 / num1); // 나눗셈
                        }

                    }
                }
            }

            // 목표 숫자가 dp[k]에 있다면 종료
            if (dp.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }
}