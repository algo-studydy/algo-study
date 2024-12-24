import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = n - 2;
        int d = 1_000_000_007;
        int[] a = new int[n + 1];
        a[1] = 1;
        a[2] = 1;

        for (int i = 3; i <= n; i++) {
            a[i] = (a[i - 1] + a[i - 2])%d;
        }

        System.out.println(a[n] + " " + b);
    }
}
