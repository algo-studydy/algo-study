import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dpmax = new int[3];
        int[] dpmin = new int[3];

        while (n-->0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int[] tmp = dpmax.clone();
            dpmax[0] = Math.max(tmp[0],tmp[1]) + a;
            dpmax[1] = Math.max(Math.max(tmp[0],tmp[1]),tmp[2]) + b;
            dpmax[2] = Math.max(tmp[1],tmp[2]) + c;

            tmp = dpmin.clone();
            dpmin[0] = Math.min(tmp[0],tmp[1]) + a;
            dpmin[1] = Math.min(Math.min(tmp[0],tmp[1]),tmp[2]) + b;
            dpmin[2] = Math.min(tmp[1],tmp[2]) + c;

        }

        int max = 0;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<3; i++){
            max = Math.max(max, dpmax[i]);
            min = Math.min(min, dpmin[i]);
        }
        System.out.println(max+" "+min);
    }
}
