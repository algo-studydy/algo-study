import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int Q = sc.nextInt();

        int[][] map = new int[R+1][C+1]; // (1,1) 부터 (r,c)까지의 구간합 배열

        for(int i=1; i<R+1; i++){
            for(int j=1; j<C+1; j++){
                int num = sc.nextInt();
                map[i][j] = map[i-1][j] + map[i][j-1] + num - map[i-1][j-1];
            }
        }

        for(int i=0; i<Q; i++){
            int r1 = sc.nextInt();
            int c1 = sc.nextInt();
            int r2 = sc.nextInt();
            int c2 = sc.nextInt();
            int sum = map[r2][c2] - map[r2][c1-1] - map[r1-1][c2] + map[r1-1][c1-1];
            int ans = sum / ((r2-r1+1)*(c2-c1+1));
            System.out.println(ans);
        }
    }
}