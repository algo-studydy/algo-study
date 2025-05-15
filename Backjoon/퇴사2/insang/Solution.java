import java.util.Arrays;
import java.util.Scanner;

public class BOJ15486퇴사2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // dp[i + time[i]] 접근 시 n + 1까지 안전하게 접근 가능
        int[] time = new int[n+2];
        int[] price = new int[n+2];
        int[] dp = new int[n+2];

        for(int i = 1; i <= n; i++){
            int t = sc.nextInt();  // 소요일
            int p = sc.nextInt();  // 이익
            time[i] = t;
            price[i] = p;
        }

        // 뒤에서부터 계산
        for(int i = n; i >= 1; i--){
            // 소요일이 n+1 이하
            if(i + time[i] <= n+1){
                dp[i] = Integer.max(dp[(time[i] + i)] + price[i], dp[i+1]);
            }
            // 상담할 수 없으므로, 다음 날 이익을 가져옴
            else{
                dp[i] = dp[i+1];
            }
        }
        System.out.println(dp[1]);
    }
}
