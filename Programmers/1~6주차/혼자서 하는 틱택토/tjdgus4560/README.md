# [Programmers] 혼자서 하는 틱택토

### 접근법

- 그리디로 불가능한 조건들을 하나씩 검사해가면서 풀음
- 불가능한 조건들
  1. O가 X+1보다 많으면 안됨
  2. X가 O보다 많으면 안됨
  3. O와 X가 동시에 승리하면 안됨
  4. X가 승리했을 때 O의 개수가 X보다 많으면 안됨
  5. O가 승리했을 때 O와 X의 개수가 같으면 안됨