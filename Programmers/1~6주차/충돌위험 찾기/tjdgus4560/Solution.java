
import java.util.*;
// 1 로봇이동, 2시간저장, 2위험체크
class Solution {
    public static int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 시간대별 로봇 위치 저장
        // HashMap<좌표, HashMap<시각, 방문횟수>
        HashMap<String, HashMap<Integer, Integer>> map = new HashMap<>();

        // 로봇의 갯수만큼 반복
        for(int i = 0; i < routes.length ; i++){

            int time = 0;

            // i번째 로봇의 움직임 표시
            for(int j = 0 ; j < routes[i].length-1 ; j++){
                int R = points[routes[i][j] - 1][0];
                int C = points[routes[i][j] - 1][1];

                int targetR = points[routes[i][j+1] - 1][0];
                int targetC = points[routes[i][j+1] - 1][1];
                if( j == 0){
                    String position = R + "," + C;
                    map.putIfAbsent(position, new HashMap<>());
                    HashMap<Integer, Integer> timeMap = map.get(position);
                    timeMap.put(time, timeMap.getOrDefault(time, 0) + 1);
                }

                while(R != targetR || C != targetC){
                    // R의 변화 먼저
                    if (R != targetR) {
                        R += (targetR > R) ? 1 : -1;
                    } else if (C != targetC) {
                        C += (targetC > C) ? 1 : -1;
                    }

                    time++;
                    // 현재 위치를 문자열로 변환하여 저장
                    String position = R + "," + C;
                    map.putIfAbsent(position, new HashMap<>());
                    HashMap<Integer, Integer> timeMap = map.get(position);
                    timeMap.put(time, timeMap.getOrDefault(time, 0) + 1);


                }
            }
        }

        // 시간별 위치에서의 충돌 횟수 계산
        for (HashMap<Integer, Integer> timeMap : map.values()) {
            for (int count : timeMap.values()) {
                if (count > 1) {
                    answer += 1;
                }
            }
        }
        return answer;
    }
}