# [Backjoon] N번째 큰 수

### 접근법

- 단순히 우선순위 큐에 모든 값들을 집어넣고 N번째 큰 수를 뽑아내면 됨
- 우선순위 큐를 선언할 때 아래처럼 선언하면 내림차순으로 정렬 가능 
```
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
```
