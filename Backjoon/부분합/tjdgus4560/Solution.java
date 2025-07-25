import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] sum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
        }

        // 누적합 배열이 있을 때
        // 구간 a~b(b>a) 의 합은 sum[b]-sum[a]
        // {0,1,2,3} , 3,1
        // 구간합이 s이상인 것들중 최소의 길이
        // front 를 늘려가면서 더큰 구간을 탐색
        // 구간합이 s와 같거나 클때 rear을 올린다
        // b-a 이 구간의 길이
        int front = 1;
        int rear = 0;
        int min = Integer.MAX_VALUE;

        while(rear <= front  && front <= n){
            int num=sum[front] - sum[rear];
            if(num >= s){
                min = Math.min(min, front - rear);
                if(min == 1){
                    System.out.println(1);
                    return;
                }
                rear++;
            }else{
                front++;
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0:min);
    }
}
