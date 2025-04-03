# [Programmers] 알고리즘 수업 - 깊이 우선 탐색 1

### 접근법

- 처음에 그래프를 연결리스트로 표현 -> 정렬하는데 시간 오버남
- 인접리스트 정렬 Collections.sort 이용
- 그래프를 ArrayList로 표현 후 dfs 수행하니 해결