# [Programmers] 양궁대회

### 접근법

-   N의 값이 크지 않아서 백트래킹으로 모든 경우의 수를 탐색
-   info[i] >= result[i]를 통해 어피치를 이길 수 있는 최소한의 화살만 사용
-   만약, 화살을 다 쐈을 경우 lion과 apeach의 점수를 구함
-   해당 값이 maxValue보다 클 경우 ans[i]를 새로운 슈팅 방법으로 갱신
-   만약, 같다면 result의 뒤에서부터 ans와 비교하여 어떤 배열이 더 낮은 점수를 쐈는지 판단하고 갱신 혹은 유지
