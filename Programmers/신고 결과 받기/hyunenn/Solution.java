import java.util.*;

class Solution {
    // 누가 누구를 신고했는지 알수있는 방법
    static Set<String> uniq = new HashSet<>();
    // 사람별 신고받은 총 횟수
    static Map<String, Integer> map = new HashMap<>();
    // A가 신고해서 정지당한 유저의 수 카운트
    static Map<String, List<String>> list = new HashMap<>();
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        for(int i=0;i<report.length;i++) {
            String[] str = report[i].split(" ");
            String first = str[0];
            String second = str[1];

            if(uniq.contains(report[i])) continue;

            uniq.add(report[i]);
            map.put(second, map.getOrDefault(second, 0) + 1);

            if(!list.containsKey(first)) {
                list.put(first, new ArrayList<>());
            }
            list.get(first).add(second);
        }

        // for(Map.Entry<String, Integer> pair : map.entrySet()) {
        //     System.out.println(pair.getKey() + " " + pair.getValue());
        // }

        for(int i=0;i<id_list.length;i++) {
            int sum = 0;
            if(!list.containsKey(id_list[i])) {
                answer[i] = 0;
                continue;
            }

            for(int j=0;j<list.get(id_list[i]).size();j++) {
                String s = list.get(id_list[i]).get(j);
                int now = map.get(s);
                if(now >= k) sum++;
            }
            answer[i] = sum;
        }
        return answer;
    }
}

/**
 1. A가 B를 여러번 신고해도, 1번으로 체크
 2. k번 이상 신고된 유저는 게시판 정지

 */
