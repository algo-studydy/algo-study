import java.util.*;

public class Main {
    static int[] dr = {-1,0,1,0,-1,-1,1,1}; //상우하좌 , 좌상부터 시계방향 대각선
    static int[] dc = {0,1,0,-1,-1,1,1,-1};
    static int n;
    static int m;
    static int[][] grid;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        // Please write your code here.
        int answer = 0;
        for(int k=0; k<=n; k++){
            answer = Math.max(dril(k), answer);
        }

        System.out.println(answer);
    }

    public static int dril(int k){

        int max = 0;
        int cost = k*k + (k+1)*(k+1);

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int gold = 0;

                for(int r=i-k; r<=i+k; r++){
                    for(int c = j-k; c<=j+k; c++){
                        if(Math.abs(i-r) + Math.abs(j-c) > k) continue;
                        if(r < 0 || r >= n || c < 0 || c >= n) continue;
                        gold += grid[r][c];
                    }
                }

                if(gold*m >= cost) {
                    max = Math.max(max, gold);
                }
            }
        }

        return max;
    }
}