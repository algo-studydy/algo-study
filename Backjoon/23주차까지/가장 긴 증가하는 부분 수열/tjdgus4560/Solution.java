import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            dp[i] = 1; // 자기자신 길이1
        }

        // i번째 원소의 최대 길이를 아래부터 채워나가는 점화식
        for(int i = 1; i < n; i++) {
            // 만약 i번째 원소가 j번째 원소보다 클경우 이전까지 dp에 저장된 길이의 + 1 한값을 계속 업데이트
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int answer = 0;
        for(int i : dp){
            answer = Math.max(answer, i);
        }

        System.out.println(answer);
    }
}
