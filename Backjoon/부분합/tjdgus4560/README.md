# [Backjoon] 부분합

### 접근법

- 누적합 + 투포인터 알고리즘
- 입력을 토대로 누적합배열 생성
  - arr[i] ~ arr[j] 까지의 합은 누적합 배열 sum[j] - sum[i] 로 표현 가능
- 누적합배열을 투포인터로 맨앞2개부터 탐색하면서 조건을 만족하는 최소길이의 구간합 탐색