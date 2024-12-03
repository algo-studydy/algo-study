import java.util.*;

public class Solution {
    public int solution(int[] cards) {
        int answer = 0;

        boolean[] visited = new boolean[cards.length];  // 상자의 방문 처리를 위한 방문 배열
        List<Integer> group = new ArrayList<>();  // 그룹의 크기를 저장하기 위한 리스트

        // 모든 상자 순회
        for (int i = 0; i < cards.length; i++) {
            if (!visited[i]) {
                int size = 0;
                int index = i;

                // 현재 상자부터 다음 상자로 이동하면서 그룹 생성.
                while (!visited[index]) {
                    visited[index] = true;
                    index = cards[index] - 1; // 다음 상자로 이동
                    size++;
                }

                group.add(size); // 그룹 크기 저장
            }
        }

        // 그룹 크기 내림차순 정렬
        Collections.sort(group, Collections.reverseOrder());

        // 그룹이 1개 이하인 경우 0 리턴
        if (group.size() < 2) {
            return 0;
        }

        answer = group.get(0) * group.get(1);

        return answer;
    }
}
