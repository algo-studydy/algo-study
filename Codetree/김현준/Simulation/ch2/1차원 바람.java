import java.util.Scanner;

public class Main {
    static int n, m, q, r;
    static char d;
    static int a[][];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        a = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                a[i][j] = sc.nextInt();
        for (int i = 0; i < q; i++) {
            r = sc.nextInt();
            d = sc.next().charAt(0);

            // 현재 값 기준 바람을 불어서 이동 시킴
            // L 이면 왼쪽에서 오른쪽으로, R 이면 오른쪽에서 왼쪽으로
            if(d == 'L') moveRight(r-1, d);
            else moveLeft(r-1, d);
            // 이동 시켰을 때, 위를 향하는 로직과 아래를 향하는 로직 2개를 구현
            int mv = r-1;
            char upDir = d;
            // 위를 향함
            while(mv > 0) {
                if(isPossible(mv, -1)) {
                    if(upDir == 'L') {
                        moveLeft(mv-1, 'R');
                        upDir = 'R';
                    } else {
                        moveRight(mv-1, 'L');
                        upDir = 'L';
                    }
                    mv--;
                } else break;
            }

            mv = r-1;
            upDir = d;
            while(mv < n-1) {
                if(isPossible(mv, 1)) {
                    if(upDir == 'L') {
                        moveLeft(mv+1, 'R');
                        upDir = 'R';
                    } else {
                        moveRight(mv+1, 'L');
                        upDir = 'L';
                    }
                    mv++;
                } else break;
            }
        }
        // Please write your code here.
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    // 좌, 우 이동에 대한 함수
    public static void moveLeft(int r, char d) {
        int tmp = a[r][0];
        for(int i=0;i<m-1;i++) {
            a[r][i] = a[r][i+1];
        }
        a[r][m-1] = tmp;
    }

    public static void moveRight(int r, char d) {
        int tmp = a[r][m-1];
        for(int i=m-1;i>=1;i--) {
            a[r][i] = a[r][i-1];
        }
        a[r][0] = tmp;
    }

    public static boolean isPossible(int r, int dir) {
        for(int i=0;i<m;i++) {
            if(a[r][i] == a[r+dir][i]) return true;
        }

        return false;
    }

}