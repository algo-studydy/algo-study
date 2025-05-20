import java.util.*;

public class Main {
    static int N;
    static int[][] arr; //arr[i][0] : 마을위치  arr[i][1] : 사람의 수


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N][2];
        long sum=0; //전체 인구수 저장
        for(int i=0; i<N; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
            sum+=arr[i][1];
        }

        Arrays.sort(arr, (o1,o2) ->{
            return o1[0]-o2[0];
        });
        long count=0;

        int k=0;
        while(count<(sum+1)/2){
            count+=arr[k++][1];
        }
        System.out.println(arr[k-1][0]);
    }
}
