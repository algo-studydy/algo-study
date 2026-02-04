import java.util.*;

class Solution {
    static int[][] map;
    public int[] solution(int[][] edges) {
        map = new int[1_000_000][2];
        for(int i=0;i<edges.length;i++) {
            int f = edges[i][0];
            int s = edges[i][1];
            map[f][1]++; // 진출 차수
            map[s][0]++; // 진입 차수
        }

        // for(int i=0;i<13;i++) {
        //     System.out.println(map[i][0] + " " + map[i][1]);
        // }


        // 1. 진입 차수가 0이면서, 진출 차수가 가장 큰곳이 Vertex
        int vertex = 0; int st = 0; int stick = 0; int eight = 0;
        // 2. 막대형은 진출 차수는 없지만, 진입 차수가 있는 곳
        // 3. 8자 형은 가운데의 값이 (2, 2)가 성립하는 곳
        for(int i=0;i<map.length;i++) {
            if(map[i][0] == 0 && map[i][1] > st) {
                vertex = i;
                st = map[i][1];
            }
            if(map[i][0] > 0 && map[i][1] == 0) stick++;
            if(map[i][0] >= 2 && map[i][1] == 2) eight++;
        }

        // 4. 도넛형은 (Vertex 진출 차수 - (막대 + 8자))
        int doughnut = st - stick - eight;

        int[] answer = new int[4];
        answer[0] = vertex;
        answer[1] = doughnut;
        answer[2] = stick;
        answer[3] = eight;

        return answer;
    }
}