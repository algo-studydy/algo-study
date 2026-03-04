import java.util.*;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        boolean[] minwoo = new boolean[46];
        boolean[] result = new boolean[46];

        int zero = 0;
        for(int i = 0; i < 6; i++){
            if(lottos[i] == 0) zero++;
            minwoo[lottos[i]] = true;
            result[win_nums[i]] = true;
        }

        int min = 0;
        for(int i = 1; i < 46; i++){
            if(minwoo[i] && result[i]) min++;
        }

        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        return new int[] {rank[min+zero], rank[min]};
    }
}