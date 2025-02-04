import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int num = sc.nextInt();
                a[i][j] = num;
            }
        }

        m = sc.nextInt();
        int k = sc.nextInt();
        int[][] b = new int[m][k];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < k; j++){
                int num = sc.nextInt();
                b[i][j] = num;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < k; j++){
                int answer = 0;
                for(int l = 0; l < m; l++) {
                    answer += a[i][l] * b[l][j];
                }
                System.out.print(answer + " ");
            }
            System.out.println();
        }
    }
}
