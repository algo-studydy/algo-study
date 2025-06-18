import java.util.*;
import java.io.*;

public class Main {
    static int N, M, L;
    static int res = Integer.MAX_VALUE;
    static StringTokenizer st;
    static List<Integer> arr = new ArrayList<>();
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr.add(0);
        arr.add(L);
        for(int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(arr);

        for(int i=1;i<arr.size();i++) {
            list.add(arr.get(i) - arr.get(i-1));
        }
        Collections.sort(list);
        binarySearch();

        System.out.println(res);
    }

    public static void binarySearch() {
        int left = 1, right = L;
        while(right >= left) {
            int ans = 0;
            int mid = (left + right) / 2;
            for(int i=0;i<list.size();i++) {
                ans += (list.get(i) - 1) / mid;
            }

            if(ans <= M) {
                res = Math.min(res, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

    }
    // 6 7 800
    // 622 411 201 555 755 82

    // 3 1 1000
    // 200 701 800
    // -> 501 99
}
