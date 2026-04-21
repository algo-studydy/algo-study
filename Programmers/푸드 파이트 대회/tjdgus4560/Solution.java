import java.util.*;

class Solution {
    public String solution(int[] food) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int n = food.length;

        for(int i=1; i<n; i++){
            int size = food[i]/2;
            while(size-->0){
                sb.append(i);
            }
        }
        sb.append(0);
        for(int i=n-1; i>0; i--){
            int size = food[i]/2;
            while(size-->0){
                sb.append(i);
            }
        }

        answer = sb.toString();
        return answer;
    }
}