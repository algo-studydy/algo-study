import java.util.*;

class Solution {
    // 경로별 충돌 위험 횟수를 기록할 Map
    static final Map<String, Integer> map = new HashMap<>();

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        for (int[] route : routes) {
            int time = 0;
            for (int i = 0; i < route.length - 1; i++) {
                int start = route[i] - 1; // 시작 지점
                int end = route[i + 1] - 1; // 도착 지점

                int y = points[start][0];
                int x = points[start][1];

                // 방향 지정
                int nextY = points[end][0] - y > 0 ? 1 : -1;
                int nextX = points[end][1] - x > 0 ? 1 : -1;

                // 처음 위치 업데이트
                if (i == 0) {
                    update(time + "," + y + "," + x);
                    time++;
                }

                // y 좌표 이동
                while (y != points[end][0]) {
                    y += nextY;
                    update(time + "," + y + "," + x);
                    time++;
                }

                // x 좌표 이동
                while (x != points[end][1]) {
                    x += nextX;
                    update(time + "," + y + "," + x);
                    time++;
                }
            }
        }

        // map에서 충돌 위험 계산
        for (int count : map.values()) {
            if (count != 1) {
                answer++;
            }
        }

        return answer;
    }

    // map 업데이트 함수
    static void update(String key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }
}
