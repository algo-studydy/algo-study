import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int MOD = 15746;
        int N = Integer.parseInt(st.nextToken());
        int a = 0;
        int b = 1;
        int c = 0;

        for (int i = 1; i <= N; i++) {
            c = (a + b) % MOD;
            a = b;
            b = c;
        }

        System.out.println(c);
    }
}
