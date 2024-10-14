import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        int count = 0;
        int Rmax = 0;

        // 근무평가 내림차순, 동료평가 오름차순
        Arrays.sort(scores, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        for (int[] score : scores) {
            if (Rmax <= score[1]) {
                Rmax = score[1];
                if(wanho[0]+wanho[1] < score[0]+score[1]) {
                    count++;
                }
            }
            if(wanho[0] < score[0] && wanho[1] < score[1]) {
                return -1;
            }
        }

        int answer = count+1;
        return answer;
    }
}