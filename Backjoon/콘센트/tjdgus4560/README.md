# [Backjoon] 콘센트

### 접근법

- 정렬 & 우선순위큐를 활용한 문제
- 가장 충전시간이 긴 기기부터 콘센트에 물리고 콘센트는 pq로 구현해서 먼저 충전된 것이 나갈수 있도록 구현
- 만약 pq의 사이즈가 콘센트의 갯수만큼 되었을때 최소한 가장앞의 충전시간이 소요된다는 거이므로 time값에 추가
