import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 행렬의 개수
        int[] arr = new int[N + 1]; // 행렬의 크기를 저장할 배열

        for (int i = 0; i < N; i++) {
            int rows = sc.nextInt();  // 행렬의 행
            int cols = sc.nextInt();  // 행렬의 열
            arr[i] = rows;
            if (i == N - 1) arr[N] = cols; // 마지막 행렬의 열 저장
            // 테케 기준 {5, 3, 2, 6}순으로 저장
        }

        int[][] dp = new int[N + 1][N + 1]; // DP 배열

        // DP 배열 초기화
        for (int len = 2; len <= N; len++) { // 2부터 N까지
            for (int i = 1; i <= N - len + 1; i++) {
                int j = i + len - 1; // 부분 문제의 끝 인덱스
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) { // 분할점 k를 기준으로 나눔
                    int cost = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        System.out.println(dp[1][N]); // 최소 연산 횟수 출력
    }
}
