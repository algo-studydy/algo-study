import java.util.*;

class Solution {
    static int[][] dp;
    public int[] solution(int target) {
        dp = new int[100_001][2];
        int[] answer = new int[2];
        // 1~20은 싱글로 던질수 있는 최소의 갯수로 최대의 갯수
        for (int i = 0; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        dp[0][0] = 0; dp[0][1] = 0;

        for(int i=1;i<=20;i++) {
            dp[i][0] = 1; dp[i][1] = 1;
        }

        // 21미만이면, 바로 종료
        if(target < 21) {
            answer[0] = 1;
            answer[1] = 1;
            return answer;
        }

        // 21이상이면, 우선 현재 수에서 가능한 경우의 수는 1~60
        // 트리플, 더블, 싱글, 불 순으로 배열
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=1;i<=20;i++) map.put(i*3, 0);
        for(int i=1;i<=20;i++) map.put(i*2, 0);
        for(int i=1;i<=20;i++) map.put(i, 1);
        map.put(50, 1);

//         // 21부터 가능한 모든 수들에 대해서 검증을 시작
        for(int i=21;i<=target;i++) {
            for(int j : map.keySet()) {
                if(i - j < 0) continue;

                int next = dp[i-j][0] + 1;
                int nextSc = dp[i-j][1] + map.get(j);

                // System.out.println(next + " " + nextSc);

                if(next < dp[i][0]) {
                    dp[i][0] = next;
                    dp[i][1] = nextSc;
                } else if(next == dp[i][0]) {
                    dp[i][1] = Math.max(dp[i][1], nextSc);
                }
            }
        }

        answer[0] = dp[target][0];
        answer[1] = dp[target][1];

        return answer;
    }
}