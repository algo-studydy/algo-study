import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=0;i<2;i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;

            int[] tmp = new int[arr.length];
            int idx = 0;

            // 그 자리를 버리고, 나머지 값들로 채운다
            for(int j=0;j<arr.length;j++) {
                if(j < s || j > e) {
                    tmp[idx++] = arr[j];
                }
            }

            arr = new int[idx];
            for(int j=0;j<idx;j++) {
                arr[j] = tmp[j];
            }
        }

        System.out.println(arr.length);
        for(int i=0;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }
}
