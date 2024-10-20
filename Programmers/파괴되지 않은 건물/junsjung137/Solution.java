import java.util.*;

class Solution {
    static int[][] map;
    static int[][] deltaMap;
    static int N, M;
    static int ans;

    public int solution(int[][] board, int[][] skill) {
        initializeData(board, skill);
        solve();
        return ans;
    }

    static void initializeData(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        map = board;
        deltaMap = new int[N + 1][M + 1];

        applySkills(skill);
    }

    static void applySkills(int[][] skill) {
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            int value = (type == 1) ? -degree : degree;

            deltaMap[r1][c1] += value;
            deltaMap[r1][c2 + 1] -= value;
            deltaMap[r2 + 1][c1] -= value;
            deltaMap[r2 + 1][c2 + 1] += value;
        }
    }

    static void solve() {
        // 누적합을 통해 deltaMap을 기반으로 map 업데이트
        for (int i = 0; i < N; i++) {
            for (int j = 1; j < M; j++) {
                deltaMap[i][j] += deltaMap[i][j - 1];
            }
        }
        for (int j = 0; j < M; j++) {
            for (int i = 1; i < N; i++) {
                deltaMap[i][j] += deltaMap[i - 1][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] += deltaMap[i][j];
            }
        }

        calculateAnswer();
    }

    static void calculateAnswer() {
        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    ans++;
                }
            }
        }
    }
}
