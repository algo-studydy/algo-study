## baekjoon [문제집]

- 위상 정렬의 기본 case
- 차수가 0인 값들을 정리하고, 우선순위 큐를 통해서 현재 차수가 0인 값들을 pq 에서
- 제거하고 연결된 노드의 차수를 제거한다. 제거된 노드의 차수가 0이 되면 pq 에 넣는 식으로 진행한다
- pq 가 남아있는 경우는 사이클이 있는 그래프이므로 위상 정렬이 불가능함.