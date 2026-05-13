import java.util.*;

public class Main {
    static int[][] grid;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.

        int max = -1;

        for(int x1=0; x1<n; x1++){
            for(int y1=0; y1<m; y1++){
                for(int x2=x1; x2<n; x2++){
                    for(int y2=y1; y2<m; y2++){

                        if(isVaild(x1,y1,x2,y2)){
                            int curSize = (x2-x1+1) * (y2-y1+1);
                            max = Math.max(max, curSize);
                        }
                    }
                }
            }
        }

        System.out.println(max);
    }

    public static boolean isVaild(int x1, int y1, int x2, int y2){
        for(int i=x1; i<=x2; i++){
            for(int j=y1; j<=y2; j++){
                if(grid[i][j] <=0) return false;
            }
        }

        return true;
    }
}