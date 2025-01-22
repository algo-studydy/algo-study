import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] weight = new int[n]; //추 배열
        for(int i=0; i<n; i++){
            weight[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] marble = new int[m]; //구슬 배열
        for(int i=0; i<m; i++){
            marble[i] = sc.nextInt();
        }

        // dp[a][b] : a개의 추를 사용 했을때 b 무게의 구슬을 알아낼 수 있는지 여부를 나타내는 배열
        boolean[][] dp = new boolean[n+1][40001];
        dp[0][0] = true;

        for (int i=1; i<=n; i++) {
            for (int j=0; j<=40000; j++) {
                if (dp[i-1][j]) {
                    dp[i][j] = true; // 추를 사용하지 않는 경우
                    dp[i][j + weight[i - 1]] = true; // 추를 오른쪽에 놓는 경우
                    dp[i][Math.abs(j - weight[i - 1])] = true; // 추를 왼쪽에 놓는 경우
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i : marble){
            if(dp[n][i]){
                sb.append("Y ");
            }else{
                sb.append("N ");
            }
        }

        System.out.println(sb);
    }
}
