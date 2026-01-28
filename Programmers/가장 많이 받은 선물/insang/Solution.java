import java.util.*;

class Solution {
    static HashMap<String, HashMap<String, Integer>> give;
    static HashMap<String, HashMap<String, Integer>> receive;
    static HashMap<String, Integer> giftCnt;
    static HashMap<String, Integer> result;
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        give = new HashMap<>();  // 선물을 준 목록
        receive = new HashMap<>();  // 선물을 받은 목록
        giftCnt = new HashMap<>();  // 선물 지수
        result = new HashMap<>();  // 다음달에 받을 선물 수
        init(friends, gifts);  // Map 초기화

        for(String gift : gifts){
            String[] arr = gift.split(" ");
            String from = arr[0];  // 선물을 준 애
            String to = arr[1];  // 받은 애

            give.get(from).put(to, give.get(from).get(to) + 1);
            receive.get(to).put(from, receive.get(to).get(from) + 1);
            giftCnt.put(from, giftCnt.get(from)+1);
            giftCnt.put(to, giftCnt.get(to)-1);
        }

        for(String friend : friends){
            HashMap<String, Integer> map = give.get(friend);  // 선물을 준 친구들 목록
            for(Map.Entry<String, Integer> entry : map.entrySet()){
                String name = entry.getKey();  // 선물 받은 애
                Integer cnt = entry.getValue();   // 준 선물 수

                Integer cnt2 = give.get(name).get(friend);  // 받은 애가 준 선물 수
                if(cnt > cnt2){
                    result.put(friend, result.get(friend)+1);
                    continue;
                }

                // 서로 선물을 준 횟수가 같거나 서로 준적 없는 경우
                if(cnt == cnt2 || (cnt == 0 && cnt2 == 0)){
                    Integer giftCnt1 = giftCnt.get(friend);
                    Integer giftCnt2 = giftCnt.get(name);
                    if(giftCnt1 > giftCnt2) result.put(friend, result.get(friend) +1);
                    else if(giftCnt1 > giftCnt2) result.put(name, result.get(name) +1);
                }
            }
        }

        for(Integer cnt : result.values()){
            answer = Math.max(answer, cnt);
        }
        // System.out.println();
        return answer;
    }
    public static void init(String[] friends, String[] gifts){
        //  give, get, giftCnt, result 초기화
        for(String friend : friends){
            give.put(friend, getMap(friend, friends));
            receive.put(friend, getMap(friend, friends));
            giftCnt.put(friend, 0);
            result.put(friend, 0);
        }
    }

    public static HashMap<String, Integer> getMap(String name, String[] friends) {
        HashMap<String, Integer> map = new HashMap<>();

        for(String friend : friends){
            if(!name.equals(friend)){
                map.put(friend, 0);
            }
        }

        return map;
    }
}