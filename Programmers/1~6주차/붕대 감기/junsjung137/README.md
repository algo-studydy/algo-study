# [Programmers] 붕대 감기

### 접근법

-   캐릭터의 현재 체력과 붕대감은 시간을 Character객체에 계속 업데이트 했습니다.
-   0초 ~ 마지막 공격이 있는 시간 크기의 배열 gameTime을 만들고 각 시간에 데미지를 저장했습니다.
-   이후에 gameTime에 따라 힐, 공격을 하며 시뮬레이션으로 Character를 업데이트했습니다.