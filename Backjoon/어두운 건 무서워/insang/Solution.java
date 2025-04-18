import java.util.*;

public class BOJ16507어두운건무서워 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt();
        int c = sc.nextInt();
        int q = sc.nextInt();

        // 누적합 배열 생성
        int[][] picture = new int[r+1][c+1];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                int num = sc.nextInt();
                // 행 단위로 값 누적
                picture[i][j+1] = picture[i][j] + num;
            }
        }

        for(int i = 0; i < q; i++){
            int r1 = sc.nextInt() - 1;
            int c1 = sc.nextInt() - 1;
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            int answer = 0;

            // r1 ~ r2행까지 반복
            for(int j = r1; j <= r2 - 1; j++){
                // c2열(누적합)에서 c1열 앞의 값을 뺌
                answer += (picture[j][c2] - picture[j][c1]);
            }
            System.out.println(answer / ((r2 - r1) * (c2 - c1)));
        }
    }
}
