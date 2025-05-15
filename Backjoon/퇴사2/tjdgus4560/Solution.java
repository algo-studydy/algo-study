import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] T = new int[n+1]; //걸리는 시간
        int[] P = new int[n+1]; //비용
        int[] dp = new int[n+2]; // dp[i] : i번째 날에 얻을 수 있는 최대 수익

        for(int i=1; i<n+1; i++){
            T[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }


        for(int i=1; i<=n+1; i++){
            dp[i] = Math.max(dp[i], dp[i-1]); //이전날과 비교 최대 수익 유지

            //i + T[i] : i번째 날에 일을 시작했을때 끝나는 day

            if(i<=n && i + T[i]<=n+1){ //i 번째 날에 일을 할 수 있다면 마치는 날에 최대 수익 반영
                dp[i + T[i]] = Math.max(dp[i + T[i]], dp[i]+P[i]);
            }
        }

        System.out.println(dp[n+1]);

    }
}
