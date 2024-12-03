class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int d = 0; // 가는 동안 배달할 수 있는 택배 용량
        int p = 0; // 오는 동안 수거할 수 있는 택배 용량

        for(int i = n-1; i >= 0; i--) { // 물류창고에서 가장 먼 곳의 집부터 처리
            d -= deliveries[i]; // 배달 가능한 택배량
            p -= pickups[i];  // 수거 가능한 택배량

            // 차량의 용량이 부족할 경우
            while(d < 0 || p < 0) {
                answer += (i+1) * 2; // 물류 창고에서 i+1번째 집까지 이동거리(왕복)

                // 이동하는 동안 옮길 수 있는 용량 추가
                d += cap;
                p += cap;
                System.out.println(d + " " + p);
            }
        }
        return answer;
    }
}