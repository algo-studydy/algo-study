import java.util.*;

class Solution {
    int min = Integer.MAX_VALUE;
    public int solution(int storey) {
        int answer = 0;
        dfs(storey,0);
        return min;
    }

    public void dfs(int num,int move){
        if(num<10){
            min = Math.min(min, Math.min(move+num, move+(10-num)+1));
            return;
        }

        dfs(num/10, move + num%10);
        dfs(num/10+1, move + (10-num%10));

    }
}