# [Backjoon] 나머지 합(답지 봄)

### 접근법

-    핵심로직 (sum[j] - sum[i]) % M = 0 -> sum[j] % M = sum[i] % M
-        현재 원소가 j 일때의 M으로 나누어 떨어지는 부분구간은 앞 원소 i에서 나누어 떨어지는 횟수의 합
