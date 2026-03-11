import java.util.*;
class Solution {
    private static HashMap<String, Integer> result;
    private static HashMap<String, Integer> in;
    private static HashMap<String, Integer> out;
    private static HashSet<String> set;
    public int[] solution(int[] fees, String[] records) {
        int defaultTime = fees[0];
        int defaultPrice = fees[1];
        int minute = fees[2];
        int price = fees[3];

        result = new HashMap<>();
        in = new HashMap<>();
        out = new HashMap<>();

        set = new HashSet<>();
        for(String record : records){
            String[] split = record.split(" ");
            set.add(split[1]);
        }
        init();  // map 초기화

        for(String record : records){
            String[] split = record.split(" ");
            int time = calc(split[0]);
            String car = split[1];

            if(split[2].equals("IN")){
                in.put(car, time);
                out.put(car, -1);  // 출차 확인용 임시값 저장
            }
            else{
                out.put(car, time);
                int totalTime = time - in.get(car);  // 주차 시간
                result.put(car, result.get(car) + (time - in.get(car)));  // 주차 시간 누적
            }
        }

        // 출차 기록 조회(출차 하지 않은 차량 찾기)
        for (Map.Entry<String, Integer> entry : out.entrySet()) {
            String car = entry.getKey();
            Integer time = entry.getValue();
            if(time == -1) {  // 출차 기록이 없는 경우
                int max = 1439 - in.get(car);
                result.put(car, result.get(car) + max);
            }
        }

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            String car = entry.getKey();
            Integer time = entry.getValue();

            if(time <= defaultTime){  // 기본요금 계산
                result.put(car, defaultPrice);
            }
            else{  // 총 주차요금 계산
                int total = defaultPrice + ((time - defaultTime + minute -1) / minute) * price;
                result.put(car, total);
            }
        }

        TreeMap<String, Integer> sortResult = new TreeMap<>(result);
        int[] answer = new int[result.size()];
        int idx = 0;
        for (Map.Entry<String, Integer> entry : sortResult.entrySet()) {
            Integer total = entry.getValue();
            answer[idx++] = total;
        }
        return answer;
    }

    public static void init(){
        for(String car : set){
            result.put(car, 0);
            in.put(car, 0);
            out.put(car, 0);
        }
    }

    public static int calc(String time){
        String[] t = time.split(":");
        int hour = Integer.parseInt(t[0]) * 60;
        int minute = Integer.parseInt(t[1]);

        return hour + minute;
    }
}