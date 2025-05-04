# [Backjoon] 용돈 관리

### 접근법

- 이진탐색으로 최소 출금값 구하는 문제
- right 의 최대값 설정할 때 1번 출금하는 경우를 생각해서 N * (하루에 사용가능한 최대금액) 으로 설정

---
### 질문
- 이진탐색할때 매번 헷갈리는 조건 설정 3가지 있는데 이거 어캐해야 하는지 알려줄사람
  - 이진탐색할때 범위를 작은쪽(또는 큰쪽)으로 조일때 right = mid-1 로 했더니 틀리고 right = mid 로 설정하니까 맞음
  - while( left <= right) 이거랑 while(left < right)
  - return left 이거랑 return mid
