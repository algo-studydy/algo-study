import java.util.*;

class Solution {

    String[][] tickets;
    boolean[] visited;
    String[] answer;
    public String[] solution(String[][] tickets) {

        this.tickets = tickets; //전역화
        visited = new boolean[tickets.length];

        Arrays.sort(this.tickets, (a,b) -> a[1].compareTo(b[1])); //도착지 기준 오름차순정렬
        ArrayList<String> path = new ArrayList<>();
        path.add("ICN");

        dfs("ICN", path);
        return answer;
    }

    public void dfs(String cur, ArrayList<String> path){
        if(answer != null) return; //이미경로찾음

        // 모든 경로찾음
        if(path.size() == tickets.length+1){
            answer = path.toArray(String[]::new);
            return;
        }

        for(int i=0; i<tickets.length; i++){
            // 이미 방문했거나 시작지가 다른경우 넘김
            if(visited[i] || !tickets[i][0].equals(cur)) continue;

            visited[i] = true;
            path.add(tickets[i][1]);
            dfs(tickets[i][1], path);

            visited[i] = false;
            path.remove(path.size()-1);
        }
    }
}