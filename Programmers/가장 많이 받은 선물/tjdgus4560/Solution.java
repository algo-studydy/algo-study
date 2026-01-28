import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        Map<String, Integer> map = new HashMap<>();

        for(int i=0; i<n; i++){
            map.put(friends[i], i);
        }

        int[][] trade = new int[n][n]; // 서로주고받은 선물들
        int[] value = new int[n]; //선물지수

        // 이전달 기록
        for(int i=0; i<gifts.length; i++){
            String[] s = gifts[i].split(" ");
            int from = map.get(s[0]);
            int to = map.get(s[1]);

            trade[from][to]++;
            value[from]++;
            value[to]--;
        }


        for(int i=0; i<n; i++){
            int num=0; // 받는 선물수
            for(int j=0; j<n; j++){
                if(i==j || trade[i][j] < trade[j][i]) continue;

                if(trade[i][j] > trade[j][i]){
                    num++;
                }else{
                    if(value[i] > value[j]) num++;
                }
            }
            answer = Math.max(answer, num);
        }
        return answer;
    }
}