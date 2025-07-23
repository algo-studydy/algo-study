import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long N, K;
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());
        long left = 0, right = N;
        while(left <= right) {
            long mid = (left + 1) * (right + 1);
            if(mid != K) {
                left++;
                right--;
            } else {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
        return;
    }
    // 가로로 짜를 갯수와 세로로 짜를 갯수를 일단 반반으로 지정해봄
    // ex) 5면 , 2 3 으로 지정했을 때 답이 14라고 가정하면
    // 그랬을 때 나올 수 있는 갯수를 세자 (가로+1) * (세로+1) ?
    // 사실 가로, 세로는 위치를 바꿔도 값이 똑같으니 가로를 1로 고정해놓고 시작해서 1씩 증가하는 개념으로 접근하면?

}
