
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] map, dp;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = 1;
        // (0,0) 시작 -> 무조건 오른쪽+1,아래+1
        for(int i=1;i<N;i++) {
            for(int j=1;j<M;j++) {
                // 현재 위치에서, (열, 행) -1이상 값들과 검증해서 진행한다
                for(int r=0;r<i;r++) {
                    for(int c=0;c<j;c++) {
                        if(dp[r][c] == -1) continue;

                        if(map[i][j] > map[r][c]) {
                            dp[i][j] = Math.max(dp[i][j], dp[r][c] + 1);
                        }
                    }
                }
            }
        }

        int max = 0;

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                max = Math.max(dp[i][j], max);
            }
        }

        System.out.println(max);

    }
}
