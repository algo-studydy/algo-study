class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int min=0;
        int zero=0;
        for(int i=0; i<6; i++){
            if(lottos[i] == 0) {
                zero++;
                continue;
            }

            for(int j=0; j<6; j++){
                if(lottos[i] == win_nums[j]){
                    min++;
                }
            }
        }

        answer[1] = min >= 2 ? 7-min : 6;
        answer[0] = answer[1]-zero == 0 ? 1 : answer[1]-zero;
        return answer;
    }
}