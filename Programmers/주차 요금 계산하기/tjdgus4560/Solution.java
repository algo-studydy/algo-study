import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        // 입차 관리맵
        HashMap<String, Integer> inMap = new HashMap<>();
        // 이용시간 관리 맵
        HashMap<String, Integer> timeMap = new HashMap<>();

        for(String record : records){
            String[] s = record.split(" ");
            String time = s[0];
            String car = s[1];
            String type = s[2];

            int minute = toMinute(time);

            if(type.equals("IN")){
                //입차
                inMap.put(car, minute);
            }else{
                //출차
                int inTime = inMap.get(car);
                int total = minute-inTime;

                timeMap.put(car, timeMap.getOrDefault(car, 0) + total);
                inMap.remove(car);
            }
        }

        // 출차되지 않은 차량 처리
        int endTime = toMinute("23:59");
        for (String car : inMap.keySet()) {
            int inTime = inMap.get(car);
            int total = endTime - inTime;
            timeMap.put(car, timeMap.getOrDefault(car, 0) + total);
        }


        // 결과처리
        List<String> carList = new ArrayList<>(timeMap.keySet());
        Collections.sort(carList);

        int[] answer = new int[carList.size()];

        for (int i=0; i<carList.size(); i++) {
            String car = carList.get(i);
            int totalTime = timeMap.get(car);

            if (totalTime <= basicTime) {
                answer[i] = basicFee;
            } else {
                int extraTime = totalTime - basicTime;
                int extraFee = ((extraTime + unitTime - 1) / unitTime) * unitFee; // a/b 올림 공식 : (a+b-1)/b
                answer[i] = basicFee + extraFee;
            }
        }

        return answer;
    }

    public int toMinute(String time){
        String[] t = time.split(":");
        return Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]);
    }
}