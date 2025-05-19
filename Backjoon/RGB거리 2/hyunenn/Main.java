import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[N][3];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1번 집 기준으로 3번 탐색
        int idx = 0;
        int answer = Integer.MAX_VALUE;
        for(int j=0;j<3;j++) {
            dp[0][j] = 10000000;
        }

        for(int j=0;j<3;j++) {
            if(j==0) {
                dp[0][j] = arr[0][j];
            } else {
                dp[0][j-1] = 10000000;
                dp[0][j] = arr[0][j];
                idx = j;
            }

            for(int i=1;i<N;i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
            }

            for(int k=0;k<3;k++) {
                if(idx == k) continue;
                answer = Math.min(answer, dp[N-1][k]);
            }

//            System.out.println(j + "번째 : ");
//            for(int i=0;i<N;i++) {
//                for(int k=0;k<3;k++) {
//                    System.out.print(dp[i][k] +" ");
//                }
//                System.out.println();
//            }
        }
        System.out.println(answer);
    }
}
