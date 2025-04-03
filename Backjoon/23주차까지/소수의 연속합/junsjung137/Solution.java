import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        boolean[] isPrime = sieve(N);
        
        List<Integer> prime = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                prime.add(i);
            }
        }

        int count = 0;
        int sum = 0;
        int left = 0;
        for (int right = 0; right < prime.size(); right++) {
            sum += prime.get(right);

            while (sum > N && left <= right) {
                sum -= prime.get(left);
                left++;
            }

            if (sum == N) {
                count++;
            }
        }
        System.out.println(count);
    }
    
    public static boolean[] sieve(int N) {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
}