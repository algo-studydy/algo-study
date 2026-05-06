import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] grid;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        // Please write your code here.


        int max=0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                max = Math.max(calc(i,j), max);
            }
        }

        System.out.println(max);
    }

    public static int calc(int r, int c){
        //block[6][3][2]
        int[][][] block = {
                {{0,0}, {0,1}, {0,2}},
                {{0,0}, {1,0}, {2,0}},

                {{0,0}, {1,0}, {1,1}},
                {{0,1}, {1,1}, {1,0}},
                {{0,0}, {0,1}, {1,1}},
                {{0,0}, {0,1}, {1,0}}
        };

        int max = 0;
        for(int a=0; a<6; a++){
            int sum = 0;
            for(int b=0; b<3; b++){
                int nr = r+block[a][b][0];
                int nc = c+block[a][b][1];
                if(nr<0 || nr>=n || nc<0 || nc>=m) continue;
                sum += grid[nr][nc];
            }
            max = Math.max(max, sum);
        }

        return max;
    }
}