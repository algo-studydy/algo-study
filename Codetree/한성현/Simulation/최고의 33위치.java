import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        // Please write your code here.

        int answer  = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int count = 0;

                for(int x=i; x<i+3&&x<n; x++){
                    for(int y=j; y<j+3&&y<n; y++){
                        if(grid[x][y] == 1) count++;
                        if(x == i+2 && y == j+2) answer = Math.max(answer, count);
                    }
                }
            }
        }


        System.out.println(answer);
    }
}