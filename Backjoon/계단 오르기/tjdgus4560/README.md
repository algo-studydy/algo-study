# [Programmers] 계단 오르기

### 접근법

- 문제에 적힌 순서의 반대로 접근해야함
  - dp[i] 번째를 밟는다면 이미 이전에 조건에 맞게 한번 뛰고 오는 점화식을 가정
  - dp[i] = score[i] + dp[i-2] 는 score[i-1]를 제끼고 온거
  - dp[i] = score[i] + score[i - 1] + dp[i-3] 는 score[i-2]를 제끼고온거
  - 위 두식중 최대값을 dp배열에 삽입