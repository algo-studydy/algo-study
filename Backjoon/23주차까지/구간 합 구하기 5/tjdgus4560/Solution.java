import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        //map : [1][1] 부터 [i][j] 까지의 구간의 합;
        int[][] map = new int[N+1][N+1];

        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                int tmp = sc.nextInt();
                map[i][j] = map[i][j-1] + map[i-1][j] + tmp - map[i-1][j-1];
            }
        }

        int x1,y1,x2,y2,ans;
        for(int i=0; i<M; i++){
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            ans = map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1];
            System.out.println(ans);
        }
    }
}
