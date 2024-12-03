import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        int point = 0;  // 요격 시스템의 시작 지점
        // 미사일 범위의 끝을 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        for(int i = 0; i < targets.length; i++) {
            // 현재 좌표와 요격 시스템의 시작 지점 비교 후 값 갱신
            if(targets[i][0] >= point) {
                point = targets[i][1];
                answer++;
            }
        }

        return answer;
    }
}