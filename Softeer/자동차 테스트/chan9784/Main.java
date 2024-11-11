import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] array;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int[N];

        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        // 이분 탐색을 위한 정렬
        Arrays.sort(array);

        for(int i = 0; i < M; i++) {
            System.out.println(binarySearch(Integer.parseInt(br.readLine())));
        }
    }

    public static int binarySearch(int num) {
        int left = 0;
        int right = N - 1;
        int mid = 0;

        while(left <= right) {
            mid = (left + right) / 2;

            if(array[mid] > num) {
                right = mid - 1;
            } else if(array[mid] < num) {
                left = mid + 1;
            } else {
                break;
            }
        }
        if(left > right) {
            return 0;
        }
        return mid * (N - 1 - mid);
    }
}
