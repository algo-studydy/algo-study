import java.util.*;
public class Main {
    static int[][] building;
    static int n,m,q;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        building = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                building[i][j] = sc.nextInt();
        int[][] queries = new int[q][4];
        for (int i = 0; i < q; i++)
            for (int j = 0; j < 4; j++)
                queries[i][j] = sc.nextInt()-1;

        // Please write your code here.

        for(int t=0; t<q; t++){
            int r1 = queries[t][0];
            int c1 = queries[t][1];
            int r2 = queries[t][2];
            int c2 = queries[t][3];

            wind(r1,c1,r2,c2);

            avg(r1,c1,r2,c2);
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(building[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void wind(int r1, int c1, int r2, int c2){
        int tmp1 = building[r1][c2];
        int tmp2 = building[r2][c2];
        int tmp3 = building[r2][c1];
        int tmp4 = building[r1][c1];

        for(int i=c2; i>c1; i--){
            building[r1][i] = building[r1][i-1];
        }

        for(int i=r2; i>r1; i--){
            building[i][c2] = building[i-1][c2];
        }

        for(int i=c1; i<c2; i++){
            building[r2][i] = building[r2][i+1];
        }

        for(int i=r1; i<r2; i++){
            building[i][c1] = building[i+1][c1];
        }
        building[r1+1][c2] = tmp1;
        building[r2][c2-1] = tmp2;
        building[r2-1][c1] = tmp3;
        building[r1][c1+1] = tmp4;

    }

    public static void avg(int r1, int c1, int r2, int c2){
        int[][] tmp = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                tmp[i][j] = building[i][j];
            }
        }


        for(int r=r1; r<=r2; r++){
            for(int c=c1; c<=c2; c++){
                int count=1;
                int sum=tmp[r][c];
                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(isVaild(nr, nc)){
                        count++;
                        sum += tmp[nr][nc];
                    }
                }
                building[r][c] = sum / count;
            }
        }
    }

    public static boolean isVaild(int r, int c){
        if(r<0 || r>=n || c<0 || c>=m) return false;
        return true;
    }
}