class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n+1][m+1]; // dp[i][j] : i,j 으로 올수 있는 최단 경로의 수

        // 물 웅덩이 표시
        boolean[][] water = new boolean[n+1][m+1];
        for (int[] p : puddles) {
            water[p[1]][p[0]] = true;
        }

        dp[1][1] = 1;

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (i==1 && j==1) continue;
                if (water[i][j]) {
                    // 물웅덩이처리
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1_000_000_007;
            }
        }

        return dp[n][m];
    }
}