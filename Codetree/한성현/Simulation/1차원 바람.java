import java.util.*;

public class Main {
    static int[][] a;
    static int n,m,q;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        a = new int[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                a[i][j] = sc.nextInt();
            }
        }

        // Please write your code here.
        for (int t = 0; t < q; t++) {
            int r = sc.nextInt()-1;
            char d = sc.next().charAt(0);

            if(d == 'R'){
                moveL(r);
            }else{
                moveR(r);
            }
            //위로전파
            char tmpD = d == 'R' ? 'L' : 'R';
            for(int y=r-1; y>=0; y--){
                if(!equalCheck(y, y+1)) break;
                if(tmpD == 'R'){
                    moveL(y);
                }else{
                    moveR(y);
                }
                tmpD =  tmpD == 'R' ? 'L' : 'R';
            }

            //아래로 전파
            tmpD = d == 'R' ? 'L' : 'R';
            for(int y=r+1; y<n; y++){
                if(!equalCheck(y, y-1)) break;
                if(tmpD == 'R'){
                    moveL(y);
                }else{
                    moveR(y);
                }
                tmpD =  tmpD == 'R' ? 'L' : 'R';
            }
        }


        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void moveL(int y){
        int tmp = a[y][0];
        for(int i=0; i<m-1; i++){
            a[y][i] = a[y][i+1];
        }
        a[y][m-1] = tmp;
    }

    public static void moveR(int y){
        int tmp = a[y][m-1];
        for(int i=m-1; i>0; i--){
            a[y][i] = a[y][i-1];
        }
        a[y][0] = tmp;
    }

    public static boolean equalCheck(int y1, int y2){
        for(int i=0; i<m; i++){
            if(a[y1][i] == a[y2][i]) return true;
        }

        return false;
    }
}