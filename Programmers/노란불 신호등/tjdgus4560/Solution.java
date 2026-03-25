import java.util.*;

class Solution {
    int[] cycles;
    int[][] signals;
    public int solution(int[][] signals) {

        this.signals = signals;

        // 각 신호등의 전체 주기 저장할 배열
        cycles = new int[signals.length];

        // 각 신호등 주기 계산
        for (int i = 0; i < signals.length; i++) {
            int g = signals[i][0];
            int y = signals[i][1];
            int r = signals[i][2];
            cycles[i] = g + y + r;
        }

        // 모든 신호등 주기의 최소공배수 까지만 보면 됨
        int totalLcm = lcmAll(cycles);

        // 겹치는거 구하기
        for (int t = 1; t <= totalLcm; t++) {
            boolean flag = true;

            // 현재 시간 t에서 모든 신호등이 노란불인지 검사
            for (int i = 0; i < signals.length; i++) {
                if (!isYellow(i, t)) {
                    // 하나라도 노란불이 아니면 실패
                    flag = false;
                    break;
                }
            }

            // 모든 신호등이 동시에 노란불이면 통과
            if (flag) {
                return t;
            }
        }

        return -1;
    }

    boolean isYellow(int i, int t) {
        // 각 신호등 주기안의 수로 치환
        int pos = (t - 1) % cycles[i] + 1;

        return signals[i][0] < pos && pos <= signals[i][0] + signals[i][1];
    }

    // 최대공약수
    public int gcd(int x, int y){

        int a = Math.max(x, y);
        int b = Math.min(x, y);
        // 큰수를 작은수로 나눠 나머지 구하기를 반복
        while(b != 0){
            int r = a % b;
            a = b;
            b = r;
        }

        // b가 0이 될때 a가 최대 공약수
        return a;
    }

    // 최대공약수
    public int lcm(int x, int y){
        return x * y / gcd(x, y);
    }

    // n 개의 최대공약수
    public int lcmAll(int[] nums){
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            result = lcm(result, nums[i]);
        }

        return result;
    }
}