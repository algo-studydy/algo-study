import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] dp = new int[N + 1]; // 입력중 증가하면서 연속된수의 입력이 들어온 경우 카운트
        int max = 0;

        for (int i=0; i<N; i++) {
            int x = sc.nextInt();

            dp[x] = dp[x-1] + 1;
            if (dp[x] > max) {
                max = dp[x];
            }
        }


        System.out.println(N - max);
    }
}
