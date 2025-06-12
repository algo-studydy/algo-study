import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static HashSet<Integer> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        for(int i=0;i<N;i++) {
            for(int j=i;j<N;j++) {
                set.add(arr[i] + arr[j]);
            }
        }
        // 자연스럽게, set 은 오름차순 정렬
        int ans = 0;
        for(int i=N-1;i>=0;i--) {
            for(int j=0;j<i;j++) {
                if(set.contains(arr[i] - arr[j])) ans = Math.max(ans, arr[i]);
            }
        }
        System.out.println(ans);
    }
}

// 세 수를 모두 탐색해서 찾는 경우의 수는 O(N^3) -> 1000^3 = 10억 -> 10초 (시간 초과)
// 그래서, 두 수의 합을 미리 hashSet 에 저장해서 O(N^2) + O(N^2) 로 접근?
