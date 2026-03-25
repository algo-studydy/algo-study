class Solution {
    static int[][] dp;

    public int[] solution(int target) {
        // dp[i][0] = i점을 만들기 위해 던진 횟수의 최솟값
        // dp[i][1] = 그때의 최대 싱글+불 횟수
        dp = new int[target + 1][2];

        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = 0;
        }
        dp[0][0] = 0;
        dp[0][1] = 0;

        for (int score = 1; score <= target; score++) {
            // 싱글 1~20
            for (int i = 1; i <= 20; i++) {
                if (score - i >= 0) {
                    update(score, score - i, true);
                }
            }

            // 더블 2~40
            for (int i = 1; i <= 20; i++) {
                int val = i * 2;
                if (score - val >= 0) {
                    update(score, score - val, false);
                }
            }

            // 트리플 3~60
            for (int i = 1; i <= 20; i++) {
                int val = i * 3;
                if (score - val >= 0) {
                    update(score, score - val, false);
                }
            }

            // 불 50
            if (score - 50 >= 0) {
                update(score, score - 50, true);
            }
        }

        return new int[]{dp[target][0], dp[target][1]};
    }

    void update(int now, int prev, boolean sb) {
        int newThrow = dp[prev][0] + 1;
        int newSB = dp[prev][1] + (sb ? 1 : 0);

        if (newThrow < dp[now][0]) {
            dp[now][0] = newThrow;
            dp[now][1] = newSB;
        } else if (newThrow == dp[now][0] && newSB > dp[now][1]) {
            dp[now][1] = newSB;
        }
    }
}