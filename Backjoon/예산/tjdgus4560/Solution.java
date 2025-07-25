import java.util.*;

public class Main {
    static int N; // 지방의 수
    static int M; // 총예산
    static int[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];

        int max=0;
        for(int i=0; i<N; i++){
            arr[i] = sc.nextInt();
            max = Math.max(arr[i], max);
        }
        M = sc.nextInt();

        int ans = binarysearch(max);
        System.out.println(ans);
    }

    private static int binarysearch(int right) {
        int left = 0;

        int ans=0;
        while(left<=right){
            int mid = (left+right)/2;

            if(isVaild(mid)){
                ans = mid;
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return ans;
    }

    private static boolean isVaild(int max) {
        int count=0;
        for(int i=0; i<N; i++){
            int tmp = arr[i];
            if(tmp>=max){
                count+=max;
            }else{
                count+=tmp;
            }
            if(count>M) return false;
        }
        return true;
    }
}
