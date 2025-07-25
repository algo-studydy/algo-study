import java.util.*;

public class BOJ1806부분합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int answer = Integer.MAX_VALUE;
        int[] prefix = new int[n+1];

        for(int i = 0; i < n; i++){
            int num = sc.nextInt();
            prefix[i+1] = prefix[i] + num;
        }

        int left = 0;
        int right = 1;
        while(left <= right && right <= n){
            // [left, right] 구간의 합이 s보다 작으면 right를 증가시켜 구간을 넓힘
            if(prefix[right] - prefix[left] < s){
                right++;
            }
            // 구간의 합이 s 이상이면 left를 증가시켜 탐색 구간을 좁힘
            else{
                left++;
                answer = Math.min(answer, right - left + 1);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }
}
