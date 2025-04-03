import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] cost = new int[n+1][3];

        // 각 집마다 색상별 비용을 기록
        for(int i = 1; i <= n; i++){
            cost[i][0] = sc.nextInt();  // red
            cost[i][1] = sc.nextInt();  // green
            cost[i][2] = sc.nextInt();  // blue
        }

        // 2~n번째 집까지, 이전 집과 비교하여 가장 작은 비용으로 누적
        for(int i = 2; i <= n; i++){
            cost[i][0] += Math.min(cost[i-1][1], cost[i-1][2]);
            cost[i][1] += Math.min(cost[i-1][0], cost[i-1][2]);
            cost[i][2] += Math.min(cost[i-1][0], cost[i-1][1]);
        }

        // 오름차순 정렬 후 n번째 집의 가장 작은 비용 출력
        Arrays.sort(cost[n]);
        System.out.println(cost[n][0]);
    }
}
