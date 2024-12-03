import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        //끝 기준 오름차순 정렬
        Arrays.sort(targets, (a,b) -> a[1] - b[1]);

        double missile = -1;
        for(int i = 0 ; i < targets.length ; i++){
            int start = targets[i][0];
            int end = targets[i][1];

            if(missile < start){
                answer++;
                missile = end-0.1;
            }
        }
        return answer;

    }
}