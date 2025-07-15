import java.util.Scanner;

public class Main {
    static int N;
    static int K;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        arr = new int[N];

        long max =0;
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
            max = Math.max(max, arr[i]);
        }

        long ans = binarysearch(max);

        System.out.println(ans);
    }

    private static long binarysearch(long right) {
        long left = 1;

        long max =1;
        while(left <= right){
            long mid = (right+left)/2;

            if(isVaild(mid)){
                left = mid+1;
                max = Math.max(max, mid);
            }else{
                right = mid-1;
            }
        }

        return max;
    }

    // K 개 이상의 갯수로 나누어 지는지 판별
    private static boolean isVaild(long mid) {
        int count =0;
        for(int i=0; i<N; i++){
            count+=arr[i]/mid;
        }

        return count >= K;
    }
}
