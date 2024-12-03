# [Programmers] 코딩 테스트 공부

### 접근법

- 알고력, 코딩력을 각각 행과 열로 갖는 dp배열 생성
  - 최대 크기를 152으로 한 이유는 최대알고력과 코딩력이 각각 150 인 경우에 dp[151][151]까지 접근하기 때문

```
if(alp_req <= i && cop_req <= j){
    int newAlp = Math.min(maxAlp, i + alp_rwd);
    int newCop = Math.min(maxCop, j + cop_rwd);
    dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j]+cost);
}
```
- 문제를 풀어서 능력을 올릴 수 있는 상황일때 위의 코드에서<br>
  ```newAlp = Math.min(maxAlp, i + alp_rwd);``` <br>
  과 같은 작업을 하지 않으면 문제를 풀 수 있는 능력을 넘어서는 경우 올바른 처리를 하지 못하므로 반드시 필요
