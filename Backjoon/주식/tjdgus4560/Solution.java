import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        while(t-->0){
            int n = sc.nextInt();
            int[] arr = new int[n];

            for(int i=0; i<n; i++){
                arr[i] = sc.nextInt();
            }

            long sum = 0;
            int max = 0;

            for(int i=n-1; i>=0; i--){
                if(arr[i] <= max){
                    sum += max-arr[i];
                }else{
                    max = arr[i];
                }
            }

            System.out.println(sum);
        }
    }
}
