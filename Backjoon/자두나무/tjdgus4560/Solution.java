import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int W = sc.nextInt();
        int[] tree = new int[T+1];
        int[][] dp = new int[T+1][W+1]; // t시간동안 w만큼 이동했을때 먹을수 있는 자두의 최대 갯수

        for(int i=1; i<=T; i++){
            tree[i] = sc.nextInt();
        }

        for (int i=1; i<=T; i++) {
            for (int j=0; j<=W; j++) {
                int cur = (j % 2) + 1; // j % 2 == 0이면 1번 나무, 1이면 2번 나무

                // 자두를 받을 수 있으면 +1
                if (tree[i] == cur) {
                    if(j==0){
                        dp[i][j] = dp[i-1][j] + 1;
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + 1; // 이전시간의 자리에서 움직였을때랑 안움직였을때 비교해서 더큰거에 +1
                    }
                } else {
                    if(j==0){
                        dp[i][j] = dp[i-1][j];
                    }else{
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]);
                    }
                }
            }
        }

        int ans =0;
        for(int i=0; i<=W; i++){
            ans = Math.max(ans, dp[T][i]);
        }
        System.out.println(ans);
    }
}
