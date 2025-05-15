import java.util.*;

public class BOJ3079입국심사 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  // 입국심사대 개수
        int m = sc.nextInt();  // 사람 수
        int[] gate = new int[n];
        long answer = 0;
        int max = 0;

        for(int i = 0; i < n; i++){
            gate[i] = sc.nextInt();
            max = Integer.max(max, gate[i]);
        }

        Arrays.sort(gate);

        long left = 0;
        long right = (long) max * m;
        while(left <= right){
            long mid = (left + right) / 2;
            long cnt = 0;  // 심사대에서 처리할 수 있는 인원
            for(int i = 0; i < n; i++){
                cnt += mid / gate[i];
                if(cnt > m) break;
            }

            if(cnt < m) left = mid + 1;
            else{
                right = mid - 1;
                answer = mid;
            }
        }

        System.out.println(answer);
    }
}
