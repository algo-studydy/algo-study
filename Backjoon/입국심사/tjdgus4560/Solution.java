import java.util.*;

public class Main {
    static int N; // 심사대 수
    static int M; // 사람 수
    static int[] arr; // 각 심사대에서 걸리는 시간

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        long ans = bsearch();
        System.out.println(ans);
    }

    private static long bsearch() {
        long left = 0;
        long right = (long)arr[N-1] * M; // 최대 시간
        long mid = 0;
        long result = right;

        while(left <= right){
            mid = (left + right) / 2;

            if(simulation(mid) >= M){
                result = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return result;
    }

    // mid 시간 동안 검사 가능한 최대 인원 구하기
    private static long simulation(long mid) {
        long result = 0;
        for(int i=0; i<N; i++){
            result += mid / arr[i];
            if(result>M) break;
        }
        return result;
    }
}
