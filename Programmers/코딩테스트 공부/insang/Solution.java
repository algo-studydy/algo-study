import java.util.Arrays;

public class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 달성해야하는 알고력과 코딩력 설정
        int maxAlp = 0, maxCop = 0;
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        // 현재 알고력과 코딩력이 목표 이상이면 0 반환
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }

        // 현재 알고력과 코딩력이 목표보다 크다면 그 값을 목표값에 맞춤
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        System.out.println(maxAlp + " " + maxCop);

        // DP 배열 생성 (알고력, 코딩력의 최소 시간)
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {  // 현재 알고력 ~ 달성해야 하는 알고력
            for (int j = cop; j <= maxCop; j++) {

                // 알고리즘 공부로 알고력 1 증가
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                // 코딩 공부로 코딩력 1 증가 (시간 1 소모)
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                // 각 문제를 풀어서 알고력과 코딩력 증가
                for (int[] problem : problems) {
                    int minAlp = problem[0];  // 문제를 풀기 위한 최소 알고력
                    int minCop = problem[1];  // 문제를 풀기 위한 최소 코딩력
                    int plusAlp = problem[2];  // 문제를 풀었을 때 증가하는 알고력
                    int plusdCop = problem[3];  // 문제를 풀었을 때 증가하는 코딩력
                    int time = problem[4];  // 문제 풀이에 필요한 시간

                    // 현재 알고력과 코딩력이 문제의 요구 능력치 이상일 때만 문제 풀이
                    if (i >= minAlp && j >= minCop) {
                        int newAlp = Math.min(maxAlp, i + plusAlp);
                        int newCop = Math.min(maxCop, j + plusCop);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + time);
                    }
                }
            }
        }

        for (int i = 0; i <= maxAlp; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        return dp[maxAlp][maxCop];
    }
}
