import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());
        System.out.println(go(A, B, C));
    }

    static long go(long A, long B, long C) {
        if (B == 1) return A % C;
        long div = go(A, B/2, C);
        if (B % 2 == 0) return (div * div) % C;
        else return (((div * div) % C) * A) % C;
    }
}