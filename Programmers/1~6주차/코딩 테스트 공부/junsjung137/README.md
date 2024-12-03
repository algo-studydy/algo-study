# [Programmers] 코딩 테스트 공부

### 접근법

-   우선 1시간 풀이에 알고력1 or 코딩력1 올려주는 문제 + problems를 list에 담았습니다.
-   그 다음 list를 모두 고려하여, alp와 cop가 가장 큰 alp와 cop에 도달할 때 까지 bfs로 완전탐색합니다.
-       이때 memo배열을 이용하여, 현재 alp, cop에 도달하는데 걸린 time이 이전보다 작을 경우만 탐색을 진행합니다.