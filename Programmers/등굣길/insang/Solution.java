class Solution {
    static int[] dr = {0, 1};
    static int[] dc = {1, 0};
    public int solution(int m, int n, int[][] puddles) {
        // 오른쪽과 아래로만 이동 가능 -> 도착 시 최단거리임을 보장
        int answer = 0;
        int[][] map = new int[n+1][m+1];
        for(int[] water : puddles){
            map[water[1]][water[0]] = -1;
        }

        int[][] dp = new int[n+1][m+1];
        dp[1][1] = 1;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(i == 1 && j == 1) continue;  // 시작점 건너뛰기
                if(map[i][j] == -1) continue;  // 물웅덩이 건너뛰기

                // 현재 좌표 기준으로, 왼쪽과 위쪽에 도달 가능한 경로의 수를 더함
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000007;
            }
        }
        return dp[n][m];
    }
}