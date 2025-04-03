import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            long a = 1;
            long b = 1;
            long c = 1;
            long d = 1;

            for (int j = 4; j <= N; j++) {
                d = (a + b);
                a = b;
                b = c;
                c = d;
            }
            sb.append(d).append("\n");
        }
        System.out.println(sb);
    }
}
