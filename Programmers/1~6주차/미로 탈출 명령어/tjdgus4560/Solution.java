class Solution {
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    // 사전 순대로 dlru 이므로 이 순서대로 dfs 하면 첫번째 나온 답이 정답
    static char[] direction = {'d', 'l', 'r', 'u'};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = Math.abs(r - x) + Math.abs(c - y);

        // 이동거리 k로 완료 못하는 경우
        if (distance > k || (k - distance) % 2 != 0) {
            return "impossible";
        }

        StringBuilder sb = new StringBuilder();
        if (dfs(n, m, x, y, r, c, k, sb)) {
            return sb.toString();
        } else {
            return "impossible";
        }
    }

    private boolean dfs(int n, int m, int x, int y, int r, int c, int k, StringBuilder sb) {
        if (k == 0) {
            return x == r && y == c;
        }

        for (int i = 0; i < 4; i++) {
            int nr = dr[i] + x;
            int nc = dc[i] + y;

            if (nr > 0 && nr <= n && nc > 0 && nc <= m) {
                // 남은거리로 이동가능한지 확인
                int distance = Math.abs(r - nr) + Math.abs(c - nc);
                if (distance <= k - 1 && ((k - 1) - distance) % 2 == 0) {
                    sb.append(direction[i]);
                    if (dfs(n, m, nr, nc, r, c, k - 1, sb)) {
                        return true;
                    }
                    // 끝문자제거
                    sb.setLength(sb.length() - 1);
                }
            }
        }

        return false;
    }
}
