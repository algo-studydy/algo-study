# [Backjoon] 오등큰수

### 접근법

- 입력을 받을때 HashMap 을 이용해 각각의 수의 등장횟수를 저장
- 수열의 인덱스를 순서대로 스택에 넣으면서 스택의 최상단에 있는 인덱스의 값의 등장횟수가 현재 인덱스의 값의 등장횟수보다 큰값이면 해당 값으로 갱신
- n번까지 진행 후 스택에 남은 인덱스들은 오등큰수가 없는 값이므로 -1로 변경