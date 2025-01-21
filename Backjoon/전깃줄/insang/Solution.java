import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 전깃줄 개수
        int answer = 0;
        int[][] line = new int[n][2];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for(int i = 0; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            line[i][0] = a;
            line[i][1] = b;
        }

        // A 전봇대 기준으로 오름차순 정렬
        Arrays.sort(line, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        // B 전봇대 기준으로 가장 긴 증가하는 부분 수열의 길이 추출
        for(int i = 0; i < n; i++){
            for(int j = 0; j < i; j++){
                if(line[j][1] < line[i][1]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                    answer = Math.max(answer, dp[i]);
                }
            }
        }

        System.out.println(n - answer);

    }
}