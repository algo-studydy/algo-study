import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tc = Integer.parseInt(st.nextToken());


        StringBuilder sb = new StringBuilder();
        int[] arr = new int[11 + 1];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;

        for (int i = 3; i <= 11; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }
        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            sb.append(arr[n]).append("\n");
        }

        System.out.print(sb);
    }
}
