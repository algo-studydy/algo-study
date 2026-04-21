import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {return b - a;});

        for(int num : works){
            pq.add(num);
        }

        for(int i=0; i<n; i++){
            int time = pq.poll();
            time--;
            if(time != 0) pq.add(time);
            if(pq.isEmpty()) break;
        }

        while(!pq.isEmpty()){
            int time = pq.poll();
            answer += time * time;
        }
        return answer;
    }
}