import java.util.*;
public class Main {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        int r = sc.nextInt() - 1;
        int c = sc.nextInt() - 1;
        // Please write your code here.

        int range = grid[r][c];

        grid[r][c] = 0;
        for (int d=0; d<4; d++) {
            for (int dist=1; dist<range; dist++) {
                int nr = r + dr[d] * dist;
                int nc = c + dc[d] * dist;

                if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
                grid[nr][nc] = 0;
            }
        }

        //중력
        for (int col=0; col<n; col++) {
            int[] tmp = new int[n];
            int idx = n - 1;

            for (int row=n-1; row>=0; row--) {
                if (grid[row][col] != 0) {
                    tmp[idx--] = grid[row][col];
                }
            }

            for (int row=0; row<n; row++) {
                grid[row][col] = tmp[row];
            }
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
