import java.util.Scanner;

public class Main {
    static int [][] cost;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 초기화, 입력
        int n = sc.nextInt();
        cost = new int [n][3];

        for (int i = 0; i < cost.length; i++) {
            for (int j = 0; j < 3; j++) {
                cost[i][j] = sc.nextInt();
            }
        }
        // 0:R, 1:G, 2:B
        // 현재 칸을 R 로 칠한다면 이전칸은 GB중에 하나에서 골라야함
        // 나머지 칸중 가장 적은 코스트를 더함
        for (int i = 1; i < cost.length; i++) {
            cost[i][0] += Math.min(cost[i-1][1], cost[i-1][2]);
            cost[i][1] += Math.min(cost[i-1][0], cost[i-1][2]);
            cost[i][2] += Math.min(cost[i-1][0], cost[i-1][1]);
        }
        System.out.println(Math.min(cost[n-1][0], Math.min(cost[n-1][1], cost[n-1][2])));



    }
}