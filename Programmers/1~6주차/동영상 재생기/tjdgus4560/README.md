# [Programmers] 동영상 재생기

### 접근법

- 입력이 "mm:ss"형식이므로 시간 계산을 위해 int로 변환하는 함수가 필요하다고 판단.
- 사용자 커맨드 배열 순회하며 조건에 맞는 동작 수행
  - 매번 커맨드 입력직전에 오프닝구간 체크 및 처리
- 모든 입력이 끝나고 오프닝 구간에 있을 수 있으니 오프닝구간 체크하여 처리
- 결과 값 다시 mm:ss형식으로 변환