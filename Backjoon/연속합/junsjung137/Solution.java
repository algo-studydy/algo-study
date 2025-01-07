import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int pre = arr[0];
        int ans = arr[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(arr[i], pre + arr[i]);
            ans = Math.max(ans, pre);
        }

        System.out.println(ans);
    }
}
