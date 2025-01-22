import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] a = sc.next().toCharArray();
        char[] b = sc.next().toCharArray();

        int[][] dp = new int[a.length+1][b.length+1];

        // 두 문자열 비교
        for(int i = 1; i <= a.length; i++){
            for(int j = 1; j <= b.length; j++){
                // 두 문자가 같을 경우 dp 배열 갱신
                if(a[i-1] == b[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
                // 두 문자가 다를 경우 이전의 값들 중 최댓값으로 갱신
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[a.length][b.length]);
    }
}
