import java.util.Scanner;

public class Main {

    static long n; // 가위질 횟수
    static long k; // 원하는값

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextLong();
        k = sc.nextLong();

        System.out.println(binarysearch() ? "YES": "NO");
    }

    private static boolean binarysearch() {
        long right = n/2;
        long left = 0;

        int ans=0;
        while(left<=right){
            long mid = (left+right)/2;

            if(calc(mid) == k){
                return true;
            }else if(calc(mid) > k){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return false;
    }

    private static long calc(long r) {
        return (long)(r+1)*(long)(n-r+1);
    }
}