import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int totalPicks = picks[0] + picks[1] + picks[2];
        int groupCount = Math.min(totalPicks, (minerals.length + 4) / 5);

        // 5개씩 묶은 그룹의 광물의 각갯수를 [dia, iron, stone] 순서로 저장
        List<int[]> groups = new ArrayList<>();
        for(int i=0; i<groupCount; i++) {
            int dia = 0;
            int iron = 0;
            int stone = 0;
            for(int j = i*5; j<Math.min(i * 5 + 5, minerals.length); j++) {
                if(minerals[j].equals("diamond")) dia++;
                else if(minerals[j].equals("iron")) iron++;
                else stone++;
            }
            groups.add(new int[]{dia, iron, stone});
        }

        // 다이아, 철 순으로 내림차순 정렬
        groups.sort((a, b) -> {
            if(a[0] != b[0]) return b[0] - a[0];
            if(a[1] != b[1]) return b[1] - a[1];
            return b[2] - a[2];
        });

        int answer = 0;

        // 돌곡 기준 피로도 가장 큰 순서대로 정렬했으므로 좋은 곡괭이부터 소모시키면서 피로도 계산
        for(int[] g : groups){
            if(picks[0] > 0){
                answer += g[0] + g[1] + g[2];
                picks[0]--;
            }else if(picks[1] > 0){
                answer += g[0]*5 + g[1] + g[2];
                picks[1]--;
            }else {
                answer += g[0]*25 + g[1]*5 + g[2];
            }
        }

        return answer;
    }
}