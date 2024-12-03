import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a,b) -> a[1]!=b[1] ? a[1]-b[1] : a[0]-b[0]);
        boolean[] isDown = new boolean[targets.length];

        int answer = 0;
        for(int i = 0; i < targets.length; i++){
            if(isDown[i]) continue;
            int e = targets[i][1];
            int n = i;
            while(n < targets.length && e > targets[n][0]){
                isDown[n++] = true;
            }
            answer++;
        }
        return answer;
    }
}