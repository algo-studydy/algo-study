# [Backjoon] 가장 긴 증가하는 부분 수열 2

### 접근법

-   입력의 크기가 최대 1,000,000이므로 2중 for문을 활용한 풀이로는 시간 초과 발생

    -   2중 for문의 시간 복잡도 : O(N^2) -> 1,000,000 X 1,000,000 = 1조

-   따라서 O(N log N)의 시간 복잡도를 가지는 이분탐색을 활용
    -   Collections.binarySearch 메서드를 통해 값이 위치할 인덱스를 찾음
    -   해당 값이 존재하지 않으면 삽일할 위치를 -(위치 + 1)로 반환
    -   LIS 리스트에서 현재 숫자가 들어갈 위치를 찾고, 최종적으로 리스트를 업데이트하거나 값을 삽입
