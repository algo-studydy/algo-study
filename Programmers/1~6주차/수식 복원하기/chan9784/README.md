# [Programmers] 수식 복원하기

### 접근법

-   우선, 답이 있는 수식과 없는 수식으로 분리
-   답이 있는 수식에서 maxDigit()을 통해 가능한 최소 진수를 구함
-   최소 진수에서 <= 9 진수까지 탐색하며 답이 있는 수식에서 가능한 진수를 구함
-   답이 없는 수식에서 maxDigit()을 통해 가능한 최소 진수를 구함
-   최소 진수에서 <= 9 진수까지 탐색을 하는데 답이 있는 수식에서 각 진수별 개수와 답이 있는 수식의 개수를 비교함, 왜냐하면 모든 수식에서 답이 나와야하므로
-   답이 없는 수식을 탐색하는데 answer가 여러개이면 ? 출력 아니면 value 출력
