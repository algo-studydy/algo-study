class Solution {
    static boolean[][] map;
    static int answer;
    static int N;

    public int solution(int n) {
        answer = 0;
        N = n;
        map = new boolean[n][n];

        func(0);
        return answer;
    }

    private void func(int row) {
        if (row == N) {  // 모든 행에 퀸을 배치했으면 정답 증가
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isVaild(row, col)) {
                map[row][col] = true;
                func(row + 1);
                map[row][col] = false;
            }
        }
    }

    private boolean isVaild(int row, int col) {
        // 같은 열 확인
        for (int i = 0; i < row; i++) {
            if (map[i][col]) return false;
        }

        // 대각선(좌상) 확인
        int r = row;
        int c = col;
        while (r > 0 && c > 0) {
            r--;
            c--;
            if (map[r][c]) return false;
        }

        // 대각선(우상) 확인
        r = row;
        c = col;
        while (r > 0 && c < N - 1) {
            r--;
            c++;
            if (map[r][c]) return false;
        }

        return true;
    }
}