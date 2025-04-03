import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력: 행렬 개수
        int n = scanner.nextInt();

        // 행렬 크기 배열
        int[] dimensions = new int[n + 1];
        for (int i = 0; i < n; i++) {
            dimensions[i] = scanner.nextInt(); // 행 크기
            dimensions[i + 1] = scanner.nextInt(); // 열 크기
        }

        // DP 배열: dp[i][j]는 i번째부터 j번째 행렬까지 곱하는 데 필요한 최소 연산 수
        int[][] dp = new int[n][n];

        // 대각선 채우기: 한 행렬만 곱할 때는 연산이 필요 없음 (0)
        for (int len = 2; len <= n; len++) { // len은 곱하려는 행렬 묶음의 길이
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // 마지막 행렬의 인덱스
                dp[i][j] = Integer.MAX_VALUE;

                // 행렬 곱셈 순서 최적화
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dimensions[i] * dimensions[k + 1] * dimensions[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        // 출력: 전체 행렬 곱셈의 최소 연산 수
        System.out.println(dp[0][n - 1]);
    }
}
