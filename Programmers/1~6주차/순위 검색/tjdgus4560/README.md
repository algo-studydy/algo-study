# [Programmers] 순위 검색

### 접근법

- 각 info에 대해서 표현 가능한 모든 query를 HashMap의 key, info의 점수를 ArrayList형태의 value 로 저장
  - 모든 경우의 수를 탐색하기 위해 dfs 이용

저장 예)
```aiignore
Key: - and backend and senior and -, Values: [50, 260]
Key: java and - and junior and -, Values: [80, 150]
Key: java and backend and junior and chicken, Values: [80]
Key: python and frontend and - and -, Values: [150, 210]
Key: - and backend and junior and chicken, Values: [80]
Key: java and - and - and -, Values: [80, 150]
Key: python and - and senior and chicken, Values: [50, 150, 210]
Key: python and - and - and -, Values: [50, 150, 210]
Key: cpp and - and senior and pizza, Values: [260]
Key: - and backend and senior and pizza, Values: [260]
Key: java and backend and junior and pizza, Values: [150]
Key: - and backend and junior and pizza, Values: [150]
Key: - and backend and senior and chicken, Values: [50]
Key: - and frontend and senior and -, Values: [150, 210]
Key: java and backend and - and -, Values: [80, 150]
Key: java and backend and - and pizza, Values: [150]
Key: - and frontend and - and chicken, Values: [150, 210]
Key: java and backend and junior and -, Values: [80, 150]
Key: python and backend and senior and chicken, Values: [50]
Key: java and - and - and pizza, Values: [150]
Key: python and backend and - and chicken, Values: [50]
Key: cpp and - and senior and -, Values: [260]
Key: - and backend and - and pizza, Values: [150, 260]
Key: java and backend and - and chicken, Values: [80]
Key: - and backend and - and chicken, Values: [50, 80]
Key: - and - and junior and -, Values: [80, 150]
Key: java and - and junior and pizza, Values: [150]
Key: cpp and - and - and pizza, Values: [260]
Key: cpp and backend and senior and -, Values: [260]
Key: cpp and backend and - and pizza, Values: [260]
Key: java and - and junior and chicken, Values: [80]
Key: python and frontend and senior and chicken, Values: [150, 210]
Key: - and - and junior and chicken, Values: [80]
Key: python and frontend and senior and -, Values: [150, 210]
Key: - and - and junior and pizza, Values: [150]
Key: python and backend and senior and -, Values: [50]
Key: cpp and - and - and -, Values: [260]
Key: cpp and backend and - and -, Values: [260]
Key: - and - and - and pizza, Values: [150, 260]
Key: - and - and senior and chicken, Values: [50, 150, 210]
Key: - and - and - and chicken, Values: [50, 80, 150, 210]
Key: python and - and - and chicken, Values: [50, 150, 210]
Key: - and - and - and -, Values: [50, 80, 150, 150, 210, 260]

...

```
- 표현가능한 모든 쿼리들의 점수를 오름차순으로 정렬
- 이진탐색으로 쿼리의점수 이상의 값들이 몇개있는지 탐색

---
### 유의점
  - 아예 값이 존재하지 않는 쿼리 예시또한 존재하여 map 에 해당 쿼리가 존재하는지 검사 필요
```aiignore
ArrayList<Integer> values = map.get(query);
if(values != null) {
int pos = binarySearch(values, score);
return values.size() - pos;
}
```
- 이진탐색 안했더니 터짐