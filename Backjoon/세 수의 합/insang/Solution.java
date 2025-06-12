import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class BOJ2295세수의합 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] u = new int[n];

        for(int i = 0; i < n; i++){
            u[i] = sc.nextInt();
        }

        /**
         더해지는 원소가 중복될 수 있음 (2+2+2)
         x, y, z 세 수의 합이 가능한 경우를 모두 구하려면 n^3으로 시간 초과
         x + y = k - z로 문제를 변형하여 풀이
         x + y 두 수의 조합을 set에 저장 (n^2)
         */
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                set.add(u[i] + u[j]);
            }
        }

        int answer = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int target = u[i] - u[j];  // u[i] - u[j] == k - z
                if(set.contains(target)){  // target이 set에 존재하면 정답 갱신
                    answer = Math.max(answer, u[i]);
                }
            }
        }

        System.out.println(answer);
    }
}
