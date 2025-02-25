# [Backjoon] 소수의 연속합

### 접근법

- 타겟 n 이하의 소수를 에라토스테네스의 체 알고리즘으로 탐색
- 찾아낸 소수배열 누적합 배열로 변환
- 누적합배열을 투포인터 알고리즘으로 순회하여 타겟n 탐색

---

- 에라토스테네스의 체 알고리즘 사용시 목표 N 이하의 소수를 찾을때 반복을 루트N 까지 하게 되는데  
  ```
  for(int i=2; i<=(int) Math.sqrt(n); i++){
    if(isPrime[i]){
      for(int j= i*i; j<=n; j+=i){
        isPrime[j] = false;
      }
    }
  }
  ```
  처럼 (int) Math.sqrt(n) 을 사용하게 된다면 double을 int로 강제 변환 하는 과정에서 부동소수점 오류가 발생할 수 있음
  - 이를 해결하기 위해 직접 while 문을 이용한 sqrtn을 계산


- 함수리턴시 primes.stream().mapToInt(i -> i).toArray(); 을 사용한 이유
  - 에라토스테네스의 체를 이용해 소수를 판별한다면 반복 횟수보다 많은 소수를 찾아낼 수 있음 (반복을 n의 제곱근 까지만 하므로)
    - 소수를 찾는 과정에서 하나를 찾으면 count 를 증가시키는 방법으로는 해결 불가
  - 결국 isPrime[] 배열을 전체 순회하며 소수만을 찾아 리턴해야함
    - 반환할 배열의 사이즈를 미리 알지 못하므로 Arraylist 이용
    - list.toArray() 를 단순하게 사용하면 Object 형을 반환 하므로 함수의 리턴형이 int[] 과는 다르므로 반환이 불가
    - .stream().mapToInt(i -> i) 로 IntStream 을 만든후 .toArray()를 하면 int[]가 반환됨