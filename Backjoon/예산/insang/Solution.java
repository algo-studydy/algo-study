import java.util.Arrays;
import java.util.Scanner;

public class BOJ2512예산 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] country = new int[n];
        int sum = 0;

        for(int i = 0; i < n; i++){
            country[i] = sc.nextInt();
            sum += country[i];
        }

        int m = sc.nextInt();

        Arrays.sort(country);
        // 필요한 예산의 총합이 가능한 예산보다 작으면 종료
        if(sum <= m) {
            System.out.println(country[n-1]);
            return;
        }
        int answer = 0;
        int left = 1;
        int right = country[n-1];  // 가장 많이 필요한 예산
        while(left <= right){
            int mid = (left + right) / 2;  // 예산 상한선
            int money = 0;  // mid를 기준으로 예산을 지급 했을 경우 필요한 총 예산

            for(int i = 0; i < n; i++){
                // 해당 지방의 필요 예산이 mid보다 작으면 작은 예산을 money에 더해줌
                if(country[i] < mid) money += country[i];
                    // mid 이상이면 mid를 더해줌
                else money += mid;
            }

            if(money > m){
                right = mid - 1;
            }
            else{
                left = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

        System.out.println(answer);
    }
}
