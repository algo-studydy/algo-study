# [Backjoon] 공유기 설치

### 접근법

-   입력 범위와, 특정 조건에서 최댓값을 구하는 문제이므로 이분탐색 사용
-   이분 탐색을 위한 배열 정렬
-   거리 mid로 공유기 설치가 가능한지 확인 (install 함수)
    -   가능하면 더 멀리 설치할 수 있는지 확인
    -   불가능하면 거리를 줄임
