import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        // Please write your code here.


        int answer = 0;

        for(int i=0; i<n; i++){
            int count = 0;
            int prev = 101;
            for(int j=0; j<n; j++){
                if(grid[i][j] == prev){
                    count++;
                } else{
                    prev = grid[i][j];
                    count = 1;
                }
                if(count == m){
                    answer++;
                    break;
                }
            }
        }

        for(int j=0; j<n; j++){
            int count = 0;
            int prev = 101;
            for(int i=0; i<n; i++){
                if(grid[i][j] == prev){
                    count++;
                } else{
                    prev = grid[i][j];
                    count = 1;
                }
                if(count == m){
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}