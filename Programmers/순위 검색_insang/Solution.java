import java.util.*;

class Solution {
    static HashMap<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 나올 수 있는 모든 경우의 수 만들기
        for(int i = 0 ; i < info.length ; i++) {
            String[] arr = info[i].split(" ");
            dfs(arr, 0, "");
        }

        // 점수 리스트 오름차순 정렬
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        for(int i = 0 ; i < query.length ; i++) {
            query[i] = query[i].replaceAll(" and ", "");
            String[] q = query[i].split(" ");

            if(map.containsKey(q[0]))  // map에 해당 키가 존재하면 이분탐색 후 정답 갱신
                answer[i] = binarySearch(q[0], Integer.parseInt(q[1]));
        }

        return answer;
    }

    // 만들어질 수 있는 모든 조합 생성
    private static void dfs(String[] arr, int cnt, String str) {
        if(cnt == 4) {
            if(!map.containsKey(str)) {
                List<Integer> list = new ArrayList<>();
                map.put(str, list);
            }

            map.get(str).add(Integer.parseInt(arr[4]));
            return;
        }

        dfs(arr, cnt + 1, str + "-");
        dfs(arr, cnt + 1, str + arr[cnt]);
    }

    private static int binarySearch(String key, int score) {
        List<Integer> list = map.get(key);

        int start = 0;
        int end = list.size() - 1;

        while(start <= end) {
            int mid = (start + end) / 2;

            if(list.get(mid) < score)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return list.size() - start; // 점수의 총 개수 - 기준 점수 이상인 첫 번째 점수의 인덱스
    }
}
