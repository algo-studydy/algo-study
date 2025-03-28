### 접근법

-   모든 식을 순회하며 X가 들어간 수식과 아닌 수식을 나눠서 처리
    -   X가 들어가지 않은 수식을 먼저 처리하여 가능한 진법들을 필터링
    -   X가 들어간 수식은 나중에 계산하여 답을 구함
-   모든 식에서 X가 들어간 수식 찾아서 각 식의 숫자 중 가장 큰 자릿수를 구함
    -   가장 큰 자릿수보다 큰 값으로 가능한 진법 후보 생성
    -   식에서 가장 큰 숫자를 표현할 수 있는 최소한의 진법부터 고려
-   숫자가 포함된 식들을 순회하며, 각 진법에서 계산한 값이 맞지 않으면 해당 진법을 제외
    -   연산자에 따른 계산 (+ / -) 후 오른쪽 값과 비교하여 진법 필터링
-   필터링된 진법들을 이용해 X가 들어간 수식을 처리
    -   가능한 진법을 적용하여 값을 계산한 뒤, 같은 결과가 나오지 않으면 '?'로 출력
    -   같은 결과가 나오면 해당 진법에서 변환한 값으로 answer[i] 갱신
