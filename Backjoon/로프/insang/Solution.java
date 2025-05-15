import java.util.*;

public class BOJ2217로프 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Integer[] rope = new Integer[n];
        int answer = 0;

        for(int i = 0; i < n; i++){
            rope[i] = sc.nextInt();
        }

        Arrays.sort(rope, Collections.reverseOrder());

        // 내림차순 정렬 후 튼튼한 로프부터 확인
        for(int i = 0; i < n; i++){
            answer = Integer.max(answer, rope[i] * (i+1));
        }

        System.out.println(answer);
    }
}
