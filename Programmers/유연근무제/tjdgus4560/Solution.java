import java.util.*;

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        L : for(int i=0; i<schedules.length; i++){
            int schedule = schedules[i]%100 + schedules[i]/100*60;
            int tmpday = startday;
            for(int j=0; j<7; j++){
                if(tmpday%7 == 0 || tmpday%7 == 6) {
                    tmpday++;
                    continue;
                }

                int time = timelogs[i][j]%100 + timelogs[i][j]/100*60;
                if(time - schedule > 10){
                    continue L;
                }
                tmpday++;
            }
            answer++;
        }
        return answer;
    }
}