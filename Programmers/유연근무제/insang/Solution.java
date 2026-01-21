import java.util.*;
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int size = schedules.length;
        int[][] timeLog = new int[size][8];

        for(int i = 0; i < size; i++){
            int hour = schedules[i] / 100;
            int minute = schedules[i] % 100;
            if(minute + 10 >= 60){
                minute %= 10;
                hour++;
            }
            else minute += 10;
            int time = hour*100 + minute;  // 출근 인정 시각

            for(int j = 0; j < 7; j++){  // timelogs를 월~일 순서대로 배열에 저장
                timeLog[i][((j+startday-1) % 7)+1] = timelogs[i][j];
            }

            boolean flag = false;
            for(int j = 1; j < 6; j++){
                // 실제 출근 시각
                if(timeLog[i][j] > time) {
                    flag = false;
                    break;
                }
                else flag = true;
            }

            if(flag) answer++;
        }



        return answer;
    }
}