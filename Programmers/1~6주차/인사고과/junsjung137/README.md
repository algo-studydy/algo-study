# [Programmers] 인사고과

### 접근법

-   PriorityQueue<int[]> 타입인 rank에 근무 태도 + 동료 평가 점수를 기준으로 정렬했습니다. 그리고 기존 scores 인덱스도 저장했습니다.
-   rank를 따라서 순위를 매기는데, 인센티브를 받지 못하는 조건인 경우 순위에서 제외하였습니다.
-       isFailed 함수는 인센티브 수령 조건을 판단합니다.
-           scoreList는 근무 태도 점수를 기준으로 내림차순 정렬되어 있습니다. 
-           scoreList에서 먼저 근무 태도가 처음으로 큰 위치를 찾고, 이후에 동료 평가도 큰지 판단합니다.
-           큰무 태도가 큰 위치를 찾을때 이진탐색을 사용했습니다.
-       만약 동등한 점수라면, duplicated변수로 현재 순위는 유지하되 몇명이 중복된 순위인가를 저장했습니다.
