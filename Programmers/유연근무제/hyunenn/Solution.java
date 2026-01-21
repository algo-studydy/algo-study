class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int len = schedules.length;
        // 직원별 이벤트 통과 계산
        for(int i=0;i<len;i++) {
            boolean flag = true;
            // 토, 일 무시
            int time = schedules[i] + 10;
            if(time % 100 >= 60) {
                time = time + 100 - 60;
            }
            // System.out.println(time);
            for(int j=0;j<timelogs[i].length;j++) {
                int date = (startday - 1 + j) % 7;

                if(date == 5 || date == 6) continue;

                // 실제 시가 출근 희망 시보다 크면
                int first = timelogs[i][j] / 100;
                if(first > time / 100) {
                    flag = false;
                    break;
                }
                // 같을 경우,
                else if(first == time / 100) {
                    if(timelogs[i][j] % 100 > time % 100) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) answer++;
        }
        return answer;
    }
}