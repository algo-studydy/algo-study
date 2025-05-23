import java.util.*;

public class BOJ2141우체국 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long answer = 0;

        int n = sc.nextInt();
        long[][] village = new long[n][2];

        long total = 0;  // 모든 사람의 총합

        for (int i = 0; i < n; i++) {
            village[i][0] = sc.nextLong(); // 마을 위치
            village[i][1] = sc.nextLong(); // 마을 인구 수
            total += village[i][1];
        }

        // 마을의 위치를 기준으로 오름차순 정렬
        Arrays.sort(village, Comparator.comparingLong(a -> a[0]));

        long prefix = 0;
        for(int i = 0; i < n; i++){
            prefix += village[i][1];
            if(prefix >= (total + 1) / 2){
                answer = village[i][0];
                break;
            }
        }

        System.out.println(answer);
    }
}
