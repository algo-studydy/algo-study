import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        boolean[] isPrime = new boolean[n+1];
        isPrime[0] = isPrime[1] = true;  // 1은 소수가 아니므로 true

        // 에라토스테네스의 체 알고리즘
        for(int i = 2; i <= Math.sqrt(n); i++){
            // 현재 숫자가 소수라면 그 수의 배수는 소수가 아니므로 모두 true
            if(!isPrime[i]){
                for(int j = i*i; j <= n; j += i){
                    isPrime[j] = true;
                }
            }
        }

        int answer = 0;
        ArrayList<Integer> prime = new ArrayList<>();  // n 이하의 소수 리스트
        ArrayList<Integer> prefix = new ArrayList<>();  // 누적합 리스트
        prefix.add(0);

        for(int i = 0; i <= n; i++){
            if(!isPrime[i]){
                prime.add(i);
                prefix.add(prefix.get(prefix.size()-1) + prime.get(prime.size() -1));
            }
        }

        int left = 1;
        int right = 1;
        while(right < prefix.size()){
            int sum = prefix.get(right) - prefix.get(left-1);

            if(sum == n){
                answer++;
//                System.out.println("right : " + right + " " + "left : " + left);
            }

            // 합이 n 미만이면 right 값을 증가 시킴
            if(sum < n){
                right++;
            }
            // 합이 n 이상이면 left 값을 증가 시켜 구간의 길이를 단축 시킴
            else{
                left++;
            }
        }

        System.out.println(answer);
    }
}
