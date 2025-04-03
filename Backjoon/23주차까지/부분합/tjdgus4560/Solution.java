import java.util.Scanner;

public class Main {
    static int N,S;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        int[] sum = new int[N+1];
        for(int i=1; i<=N; i++){
            sum[i] = sum[i-1]+sc.nextInt();
        }
        if(sum[N] < S){
            System.out.println(0);
            return;
        }
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = 1;
        while(left < right && right <=N){
            int diff = sum[right] - sum[left];
            if(diff >= S){
                min = Math.min(min, right-left);
                left++;
            }else{
                right++;
            }
        }
        System.out.println(min != Integer.MAX_VALUE ? min : 0);
    }
}
