import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n+1][n+1]; // win[i][j]==true : i가 j 를 이겼다

        for(int[] result : results){
            win[result[0]][result[1]] = true;
        }

        // 플로이드 워셜
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    // i가 k를 이기고 k도 j를 이기면 i는 k를 이긴다
                    if(win[i][k] && win[k][j]) win[i][j] = true;
                }
            }
        }

        int[] count = new int[n+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(win[i][j]){
                    count[i]++;
                    count[j]++;
                }
            }
        }
        int answer = 0;
        for(int i=1; i<=n; i++){
            if(count[i] == n-1) answer++;
        }

        return answer;
    }
}