import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if(n<2){
            System.out.println(0);
            return;
        }
        int[] primes = getPrimes(n);
        // 소수의 누적합 배열 생성
        int[] sum = new int[primes.length+1];
        for(int i=1; i<sum.length; i++){
            sum[i] = sum[i-1] + primes[i-1];
        }
        int answer = 0;
        int left=0;
        int right=1;
        while(left < right && right < sum.length){
            int num = sum[right] - sum[left];
            if(num == n){
                answer++;
                left++;
                right++;
            }else if(num > n){
                left++;
            }else{
                right++;
            }
        }

        System.out.println(answer);
    }

    private static int[] getPrimes(int n) {
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        //에라토스테네스의 체
        int sqrtn = 1;
        while (sqrtn * sqrtn <= n) {
            sqrtn++;
        }
        sqrtn--;

        for(int i=2; i<=sqrtn; i++){
            if(isPrime[i]){
                for(int j= i*i; j<=n; j+=i){
                    isPrime[j] = false;
                }
            }
        }

        //소수만 리턴
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes.stream().mapToInt(i -> i).toArray();
    }
}
