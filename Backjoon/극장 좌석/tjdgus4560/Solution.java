import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] dp = new int[n+1]; // dp[i] : i 개의 좌석이 있을때 가질 수 있는 최대의 경우의수

        Set<Integer> set = new HashSet<>();
        for(int i=0; i<m; i++){
            set.add(sc.nextInt());
        }
        dp[1] = 1;

        if(n==1){
            System.out.println(1);
            return;
        }

        if(set.contains(1) || set.contains(2)) {
            dp[2] = 1;
        }else{
            dp[2] = 2;
        }

        for(int i=3; i<=n; i++){
            if(set.contains(i) || set.contains(i-1)){
                dp[i] = dp[i-1];
            }else{
                dp[i] = dp[i-1] + dp[i-2];
            }
        }

        System.out.println(dp[n]);
    }
}
