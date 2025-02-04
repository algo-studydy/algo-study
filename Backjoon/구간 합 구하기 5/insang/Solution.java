import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] nums = new int[n][n];  // 원본 배열
        int[][] arr = new int[n+1][n+1];  // 누적합 배열

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                nums[i][j] = sc.nextInt();
                // 가로 방향으로 누적합
                arr[i+1][j+1] = arr[i+1][j] + nums[i][j];
            }
        }

        for(int i = 0; i < m; i++){
            int answer = 0;
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();
            for(int j = x1; j <= x2; j++){
                answer += arr[j][y2] - arr[j][y1-1];
            }

            System.out.println(answer);
        }
    }
}
