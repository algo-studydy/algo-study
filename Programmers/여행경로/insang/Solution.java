import java.util.*;

class Solution {
    static Map<String, PriorityQueue<String>> map;
    static LinkedList<String> answer = new LinkedList<>();

    public String[] solution(String[][] tickets) {
        int size = tickets.length;

        map = new HashMap<>();

        for(String[] route : tickets){
            if (!map.containsKey(route[0])) {
                map.put(route[0], new PriorityQueue<>());
            }
            // 동일한 출발지가 존재할 경우 도착지를 큐에 담음
            map.get(route[0]).offer(route[1]);
        }

        dfs("ICN");
        return answer.toArray(new String[0]);
    }

    public static void dfs(String start){
        // 다음에 방문할 공항이 없다면 pq는 비어 있거나 null 상태
        PriorityQueue<String> pq = map.get(start);

        while(pq != null && !pq.isEmpty()){
            String next = pq.poll();
            dfs(next);
        }
        // 더 이상 방문할 공항이 없으면 리스트에 추가
        // 경로의 역순으로 추가되기 때문에 addFirst로 앞에서부터 추가
        answer.addFirst(start);
    }
}