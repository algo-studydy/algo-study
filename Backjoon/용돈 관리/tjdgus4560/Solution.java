import java.util.*;


public class Main{
    static int N,M;
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // N일 동안 사용
        M = sc.nextInt(); // M번 출금

        arr = new int[N];
        int min = 0;

        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
            min = Math.max(min, arr[i]); //최소한 하루의 최대 지출만큼은 되어야함
        }

        int max = N * 10000;
        int ans = binarySearch(min, max);

        System.out.println(ans);
    }

    private static int binarySearch(int left, int right) {
        int mid =0;

        while(left<right){
            mid = (left+right)/2;

            if(simulation(mid) > M){
                // M번보다 더 많이 출금했으니 더 큰값
                left = mid+1;
            }else{
                right = mid;
            }
        }

        return left;
    }

    private static int simulation(int mid) {
        int m=1;
        int cash=mid;

        for(int i=0; i<N; i++){
            if(arr[i] > cash){
                m++;
                cash = mid;
                cash-=arr[i];
            }else{
                cash-=arr[i];
            }
        }

        return m;
    }
}
