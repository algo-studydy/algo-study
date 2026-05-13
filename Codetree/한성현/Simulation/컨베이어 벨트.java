import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int t = sc.nextInt();
        int[] top = new int[n];
        int[] bottom = new int[n];
        for (int i = 0; i < n; i++) {
            top[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            bottom[i] = sc.nextInt();
        }
        // Please write your code here.
        int time = t%(n*2);

        while(time-->0){
            int tmpT = top[n-1];
            int tmpB = bottom[n-1];

            for(int i=n-1; i>0; i--){
                top[i] = top[i-1];
            }

            for(int i=n-1; i>0; i--){
                bottom[i] = bottom[i-1];
            }

            top[0] = tmpB;
            bottom[0] = tmpT;
        }

        for(int i=0; i<n; i++){
            System.out.print(top[i]+" ");
        }
        System.out.println();
        for(int i=0; i<n; i++){
            System.out.print(bottom[i]+" ");
        }
    }
}