class Solution {
    public long solution(int[][] signals) {
        int n = signals.length;

        // 각 신호등의 주기 계산
        long[] cycle = new long[n];
        for (int i = 0; i < n; i++) {
            cycle[i] = signals[i][0] + signals[i][1] + signals[i][2];
        }

        // 모든 주기의 최소공배수 계산
        long lcmVal = cycle[0];
        for (int i = 1; i < n; i++) {
            lcmVal = lcm(lcmVal, cycle[i]);
        }

        // 1초부터 LCM까지 탐색
        for (long t = 1; t <= lcmVal; t++) {
            boolean allYellow = true;
            for (int i = 0; i < n; i++) {
                long mod = (t - 1) % cycle[i];
                int g = signals[i][0];
                int y = signals[i][1];
                // 노란불 구간: [G, G+Y-1]
                if (mod < g || mod >= g + y) {
                    allYellow = false;
                    break;
                }
            }
            if (allYellow) return t;
        }

        return -1;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}