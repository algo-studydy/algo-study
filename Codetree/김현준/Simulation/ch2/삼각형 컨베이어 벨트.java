
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] l = new int[n];
        int[] r = new int[n];
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            r[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            d[i] = sc.nextInt();
        }
        // Please write your code here.

        while(t > 0) {
            int tmp = l[n-1];
            int tmp2 = r[n-1];
            int tmp3 = d[n-1];

            for(int i=n-1;i>=1;i--) {
                l[i] = l[i-1];
                r[i] = r[i-1];
                d[i] = d[i-1];
            }

            r[0] = tmp;
            d[0] = tmp2;
            l[0] = tmp3;

            t--;
        }

        for(int i=0;i<n;i++) {
            System.out.print(l[i] + " ");
        }
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.print(r[i] + " ");
        }
        System.out.println();
        for(int i=0;i<n;i++) {
            System.out.print(d[i] + " ");
        }

    }
}