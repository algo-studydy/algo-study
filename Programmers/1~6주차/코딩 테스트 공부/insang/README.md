### 접근법

-   문제들 중에서 가장 큰 요구 알고력과 코딩력을 찾아 해당 값들을 목표로 설정
-   목표값+1 크기의 2차원 DP 배열 생성
-   현재 알고력과 코딩력이 목표값 이상이라면 문제를 풀 필요가 없으므로 0 반환

-   DP 진행
    -   현재 알고력과 코딩력을 순회하며 가능한 모든 상태를 탐색
    -   (현재 알고력 + 1 < 최고 알고력)이면 알고력 1증가, 시간 1소요
    -   (현재 코딩력 + 1 < 최고 코딩력)이면 코딩력 1증가, 시간 1소요
    -   현재 알고력과 코딩력이 문제의 요구 능력치 이상일 때 문제 풀이 후 DP배열 업데이트
