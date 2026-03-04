class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount = 0;
        int alias = 0;
        for(int i=0;i<lottos.length;i++) {
            if(lottos[i] == 0) {
                zeroCount++;
                continue; // 0인 경우는 무시하고 진행
            }
            for(int j=0;j<win_nums.length;j++) {
                if(lottos[i] == win_nums[j]) {
                    alias++;
                    break;
                }
            }
        }

        int high = 7 - (alias + zeroCount);
        int low = 7 - alias;

        if(high > 6) high = 6;
        if(low > 6) low = 6;

        int[] answer = new int[2];
        answer[0] = high; answer[1] = low;
        return answer;
    }
}