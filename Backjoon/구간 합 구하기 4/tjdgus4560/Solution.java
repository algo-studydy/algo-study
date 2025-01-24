import java.util.Scanner;

public class Main {
    static int[] sum; //누적합 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sum = new int[N];
        sum[0] = sc.nextInt();
        for(int i=1; i<N; i++){
            sum[i] = sum[i-1] + sc.nextInt();
        }

        for(int i=0; i<M; i++){
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            if(a != 0){
                System.out.println(sum[b] - sum[a-1]);
            }else{
                System.out.println(sum[b]);
            }
        }
    }
}
