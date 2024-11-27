class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int size = deliveries.length;
        int aPoint = size - 1;
        int bPoint = size - 1;
        
        while (aPoint >= 0 || bPoint >= 0) {
            while (aPoint >= 0 && deliveries[aPoint] == 0) aPoint--;
            while (bPoint >= 0 && pickups[bPoint] == 0) bPoint--;
            
            int point = Math.max(aPoint, bPoint);
            
            if (point < 0) break;
            
            answer += (point + 1) * 2L;

            int aCap = cap;
            int bCap = cap;

            while (aCap > 0 && aPoint >= 0) {    // 배달
                if (deliveries[aPoint] > aCap) {
                    deliveries[aPoint] -= aCap;
                    aCap = 0;
                } else {
                    aCap -= deliveries[aPoint];
                    deliveries[aPoint] = 0;
                    aPoint--;
                }
            }
            
            while (bCap > 0 && bPoint >= 0) {    // 수거
                if (pickups[bPoint] > bCap) {
                    pickups[bPoint] -= bCap;
                    bCap = 0;
                } else {
                    bCap -= pickups[bPoint];
                    pickups[bPoint] = 0;
                    bPoint--;
                }
            }
        }
        
        
        return answer;
    }
}