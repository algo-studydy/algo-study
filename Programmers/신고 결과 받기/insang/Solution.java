import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashSet<String> reportSet = new HashSet<>(Arrays.asList(report));  // 중복 신고 제거
        Map<String, List<String>> reportMap = new HashMap<>();  // 유저별 신고한 대상 목록 저장
        Map<String, Integer> countMap = new HashMap<>();  // 유저별 신고 당한 횟수 저장

        for (String id : id_list) {
            reportMap.put(id, new ArrayList<>());
            countMap.put(id, 0);
        }

        for (String r : reportSet) {
            String[] split = r.split(" ");
            String user = split[0];  // 신고한 사람
            String userReport = split[1];  // 신고 당한 사람

            reportMap.get(user).add(userReport);
            countMap.put(userReport, countMap.get(userReport) + 1);
        }

        // 정지된 유저 확인
        for (int i = 0; i < id_list.length; i++) {
            String user = id_list[i];
            int cnt = 0;

            for (String reportUser : reportMap.get(user)) {
                if (countMap.get(reportUser) >= k) {
                    cnt++;
                }
            }
            answer[i] = cnt;
        }

        return answer;
    }
}