# [Programmers] 순위 검색

### 접근법

-   Applicant 객체를 통해 info 자료를 계층적으로 저장했습니다.
-       List<Applicant> sub = 하위 노드
-       List<Integer> scores = leaf 노드(점수) 
-   query를 실행하면서 앞서 저장된 계층을 따라 데이터를 조회했습니다.
-   leaf노드에서 점수를 조회할땐 이진탐색을 했습니다.
-       이진탐색 전에 코딩테스트 점수(scores)가 오름차순 정렬된 상태입니다.