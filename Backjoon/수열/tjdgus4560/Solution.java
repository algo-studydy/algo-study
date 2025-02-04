import java.util.Scanner;

public class Main {
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        arr = new int[n];
        int sum = 0;
        for(int i=0; i<k; i++){
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        int max = sum;

        for(int i=k; i<n; i++){
            arr[i] = sc.nextInt();
            sum = sum - arr[i-k] + arr[i] ;
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
