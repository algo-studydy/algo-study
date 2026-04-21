import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int n : tangerine){
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        int size = map.size();
        int[][] arr = new int[size][2];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {return b - a;});
        for(int key : map.keySet()){
            pq.add(map.get(key));
        }

        while(k>0){
            k-=pq.poll();
            answer++;
        }
        return answer;
    }
}