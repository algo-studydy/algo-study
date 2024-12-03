class Solution {
    static String answer;
    static int[] dr = {1, 0, 0, -1}; // 하, 좌, 우, 상
    static int[] dc = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    static int N, M, K;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        K = k;
        answer = "impossible";

        dfs(x, y, r, c, K, "");

        return answer;
    }

    private void dfs(int x, int y, int r, int c, int moveCnt, String direction) {
        // 정답을 찾은 경우 리턴
        if (!answer.equals("impossible")) return;

        // 목표 지점에 도달했을 경우
        if (moveCnt == 0) {
            if (x == r && y == c) answer = direction;
            return;
        }

        int dist = Math.abs(x - r) + Math.abs(y - c);  // 목표 지점까지 거리
        // 남은 이동 횟수로 도달 불가능한 경우 or
        // 남은 이동 횟수 - 목표 거리 = 홀수이면 리턴
        if (dist > moveCnt || (moveCnt - dist) % 2 != 0) return;

        // 사전순으로 방향 탐색
        for (int i = 0; i < 4; i++) {
            int nr = x + dr[i];
            int nc = y + dc[i];

            // 미로 범위를 벗어난 경우 continue
            if (nr < 1 || nr > N || nc < 1 || nc > M) continue;

            // 다음 방향 이동
            dfs(nr, nc, r, c, moveCnt - 1, direction + dir[i]);
        }
    }
}
