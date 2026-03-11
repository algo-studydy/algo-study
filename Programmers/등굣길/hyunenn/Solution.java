class Solution {
    static int[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        dp = new int[n][m];
        for(int i=0;i<puddles.length;i++) {
            int r = puddles[i][0] - 1;
            int c = puddles[i][1] - 1;
            // System.out.println(r + " " + c);
            dp[c][r] = -1;
        }

        for(int i=1;i<dp[0].length;i++) {
            if(i == 1 && dp[0][i] != -1) {
                dp[0][i] = 1; continue;
            }
            if(dp[0][i-1] <= 0 || dp[0][i] == -1) continue;
            else dp[0][i] = 1;
        }
        for(int j=1;j<dp.length;j++) {
            if(j == 1 && dp[j][0] != -1) {
                dp[j][0] = 1; continue;
            }
            if(dp[j-1][0] <= 0 || dp[j][0] == -1) continue;
            else dp[j][0] = 1;
        }

        for(int i=1;i<dp.length;i++) {
            for(int j=1;j<dp[0].length;j++) {
                int up = dp[i-1][j]; int left = dp[i][j-1];
                if(dp[i][j] == -1) continue;
                if(dp[i-1][j] == -1) up = 0;
                if(dp[i][j-1] == -1) left = 0;

                dp[i][j] = (up + left) % 1_000_000_007;
            }
        }

        answer = dp[n-1][m-1];

        // for(int i=0;i<dp.length;i++) {
        //     for(int j=0;j<dp[0].length;j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        return answer;
    }
}