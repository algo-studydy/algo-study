import java.util.Arrays;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = alp;
        int maxCop = cop;
        //구해야하는 능력 찾기
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        if(alp >= maxAlp && cop >= maxCop){
            return 0;
        }

        // dp 배열 초기화
        int[][] dp = new int[152][152];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[alp][cop] = 0;

        for(int i = alp; i <= maxAlp; i++){
            for(int j = cop; j <= maxCop; j++){
                // 한시간 써서 올리는경우
                dp[i+1][j] = Math.min(dp[i][j]+1, dp[i+1][j]);
                dp[i][j+1] = Math.min(dp[i][j]+1, dp[i][j+1]);

                for(int[] problem : problems) {
                    int alp_req = problem[0];
                    int cop_req = problem[1];
                    int alp_rwd = problem[2];
                    int cop_rwd = problem[3];
                    int cost = problem[4];

                    //만약 현재 능력이 요구사항 충족할때
                    if(alp_req <= i && cop_req <= j){
                        int newAlp = Math.min(maxAlp, i + alp_rwd);
                        int newCop = Math.min(maxCop, j + cop_rwd);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j]+cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}