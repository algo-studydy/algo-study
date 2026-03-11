import java.util.*;

class Solution {
    class Point {
        int id, time;
        Point(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> ans = new HashMap<>();
        for(int i=0;i<records.length;i++) {
            String[] s = records[i].split(" ");
            String[] t = s[0].split(":");

            int time = Integer.parseInt(t[0]) * 60 +
                    Integer.parseInt(t[1]);

            int inCar = Integer.parseInt(s[1]);

            if(map.containsKey(inCar)) {
                int inTime = map.get(inCar);
                map.remove(inCar);
                int remain = time - inTime;
                ans.put(inCar, ans.getOrDefault(inCar, 0) + remain);
            }
            else {
                map.put(inCar, time);
            }
        }

        // map이 남아있다면, 23:59분 기준으로 출차를 찍어버린다.
        int out = 23 * 60 + 59;
        for(int key : map.keySet()) {
            int now = out - map.get(key);
            ans.put(key, ans.getOrDefault(key, 0) + now);
        }

        List<Integer> list = new ArrayList<>(ans.keySet());
        Collections.sort(list);

        int[] answer = new int[list.size()];
        int idx = 0;
        for(int i : list) {
            int money = 0;
            int sum = ans.get(i) - fees[0];
            if(sum > 0) {
                if(sum % fees[2] != 0)
                    money = (sum / fees[2] + 1) * fees[3];
                else money = (sum / fees[2]) * fees[3];
            }
            answer[idx++] = fees[1] + money;
        }

        return answer;
    }
}